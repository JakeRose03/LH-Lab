package io.littlehorse.quickstart;

import io.littlehorse.quickstart.services.FindDriver;
import io.littlehorse.quickstart.services.InitPayment;
import io.littlehorse.quickstart.services.InitiateOrder;
import io.littlehorse.quickstart.services.NotiUser;
import io.littlehorse.sdk.common.config.LHConfig;
import io.littlehorse.sdk.worker.LHTaskWorker;

public class Main {
    static LHConfig config = new LHConfig();



    public static void startTaskWorker(){

        //Start TaskWorkers
        LHTaskWorker initiateOrderWorker = new LHTaskWorker(new InitiateOrder(), "initiate-order", config);
        initiateOrderWorker.start();

        LHTaskWorker calcPayment = new LHTaskWorker(new InitiateOrder(), "calculate-payment", config);
        calcPayment.start();


        LHTaskWorker payForOrder = new LHTaskWorker(new InitiateOrder(), "pay-for-order", config);
        payForOrder.start();

        LHTaskWorker assignDriver = new LHTaskWorker(new FindDriver(), "assign-driver", config);
        assignDriver.start();

        LHTaskWorker rankDriver = new LHTaskWorker(new FindDriver(), "rank-drivers", config);
        rankDriver.start();

        LHTaskWorker availableDrivers = new LHTaskWorker(new FindDriver(), "available-drivers", config);
        availableDrivers.start();


        LHTaskWorker notiUser = new LHTaskWorker(new NotiUser(), "noti-user", config);
        notiUser.start();

        LHTaskWorker notiUserFail = new LHTaskWorker(new NotiUser(), "noti-user-failed", config);
        notiUserFail.start();

        Runtime.getRuntime().addShutdownHook(new Thread(initiateOrderWorker::close));
        Runtime.getRuntime().addShutdownHook(new Thread(assignDriver::close));


    }
    public static void regMetaData(){
        //Register your TaskWorker to Lh Server
        LHTaskWorker initiateOrderWorker = new LHTaskWorker(new InitiateOrder(), "initiate-order", config);
        initiateOrderWorker.registerTaskDef();

        LHTaskWorker calcPayment = new LHTaskWorker(new InitPayment(), "calculate-payment", config);
        calcPayment.registerTaskDef();

        LHTaskWorker payForOrder = new LHTaskWorker(new InitPayment(), "pay-for-order", config);
        payForOrder.registerTaskDef();

        LHTaskWorker assignDriver = new LHTaskWorker(new FindDriver(), "assign-driver", config);
        assignDriver.registerTaskDef();

        LHTaskWorker rankDriver = new LHTaskWorker(new FindDriver(), "rank-drivers", config);
        rankDriver.registerTaskDef();

        LHTaskWorker availableDrivers = new LHTaskWorker(new FindDriver(), "available-drivers", config);
        availableDrivers.registerTaskDef();

        LHTaskWorker notiUser = new LHTaskWorker(new NotiUser(), "noti-user", config);
        notiUser.registerTaskDef();

        LHTaskWorker notiUserFail = new LHTaskWorker(new NotiUser(), "noti-user-failed", config);
        notiUserFail.registerTaskDef();

        StartWorkFlow startWorkFlow = new StartWorkFlow();
        startWorkFlow.getWorkflow().registerWfSpec(config.getBlockingStub());

    }

    public static void main(String[] args) {
        if (args.length != 1 || (!args[0].equals("register") && !args[0].equals("workers"))) {
            System.err.println("Please provide one argument: either 'register' or 'workers'");
            System.exit(1);
        }

     if (args[0].equals("register")) {
            regMetaData();
        } else {
            startTaskWorker();
        }
    }

}