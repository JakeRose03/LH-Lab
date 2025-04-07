package io.littlehorse.quickstart;

import io.littlehorse.quickstart.Model.Order;
import io.littlehorse.sdk.common.proto.ExponentialBackoffRetryPolicy;
import io.littlehorse.sdk.wfsdk.NodeOutput;
import io.littlehorse.sdk.wfsdk.WfRunVariable;
import io.littlehorse.sdk.wfsdk.Workflow;
import io.littlehorse.sdk.wfsdk.WorkflowThread;

public class StartWorkFlow {

    public static final String WF_NAME = "door-dash-wf";
    public static final String INIT_ORDER = "initiate-order";
    public static final String CALC_PRICE  = "calculate-payment";
    public static final String AVAL_DRIVERS = "available-drivers";
    public static final String FIND_DRIVER = "assign-driver";
    public static final String RANK_DRIVER = "rank-drivers";
    public static final String INIT_PAY = "pay-for-order";
    public static final String NOTI_USER = "noti-user";
    public static final String NOTI_USER_FAIL = "noti-user-failed";

    /*
     * This method defines the logic of our workflow
     */
    public void startWf(WorkflowThread wf) {
        //DEFINING VARIABLES NEED FOR INPUT TO START WF AND CAPTURE RESULTS OF TASKS
        WfRunVariable orderVar = wf.declareJsonObj("order").searchable().required();
        WfRunVariable userVar = wf.declareJsonObj("user").searchable().required();
        WfRunVariable assignDriver = wf.declareBool("assign-driver");
        WfRunVariable avalDrivers = wf.declareStr("aval-drivers");
        WfRunVariable paymentProc = wf.declareStr("payment-res");

        //STARTING WORKFLOW
        //Step 1) initialize Order
        wf.execute(INIT_ORDER, orderVar).withRetries(3);

        //Step 2) Find available drivers store input in avalDrivers Res
        NodeOutput avalDriversRes = wf.execute(AVAL_DRIVERS).withRetries(10).withExponentialBackoff(
                ExponentialBackoffRetryPolicy.newBuilder()
                        .setBaseIntervalMs(2000)
                        .setMultiplier(2.0f)
                        .setMaxDelayMs(10000)
                        .build()
        );
        avalDrivers.assign(avalDriversRes);


        //step 4) if there are no available drivers notify user and end WF
        wf.doIf(avalDrivers.isNotEqualTo("available Drivers"),
                ifBody-> {
                    ifBody.execute(NOTI_USER_FAIL);
                    ifBody.complete();
                });

        // step 5) we found available drivers lets find the best fit/closest
        wf.execute(RANK_DRIVER);

        // step 6) assign Driver
        wf.execute(FIND_DRIVER, orderVar);

        // step 7) apply discounts for dashpass
        wf.execute(CALC_PRICE,userVar,orderVar);

        // step 8) initialize payment save result
        NodeOutput initPayRes = wf.execute(INIT_PAY,userVar,orderVar);
        paymentProc.assign(initPayRes);

        //Step 9) if Payment failed notify user of failed payment
        wf.doIf(paymentProc.isEqualTo("Payment Failed"),
                ifBody->{
            ifBody.execute(NOTI_USER_FAIL);
            ifBody.complete();
                });
        //Step 10) notify user order is complete :)
        wf.execute(NOTI_USER);
        wf.complete();

    }



    /*
     * This method returns a LittleHorse `Workflow` wrapper object that can be
     * used to register the WfSpec to the LittleHorse Server.
     */
    public Workflow getWorkflow() {
        return Workflow.newWorkflow(WF_NAME, this::startWf);
    }


}
