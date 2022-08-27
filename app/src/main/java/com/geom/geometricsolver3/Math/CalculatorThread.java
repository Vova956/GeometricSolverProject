package com.geom.geometricsolver3.Math;

import com.geom.geometricsolver3.Interfaces.IPostAction;
import com.geom.geometricsolver3.Interfaces.ISolver;

public class CalculatorThread extends Thread{
    private IPostAction action;
    private ISolver solver;
    private String result;
    private GeometryException e = null;


    public CalculatorThread(ISolver solver){
        this.solver = solver;
    }

    public void setExitStrategy(IPostAction action){
        this.action = action;
    }

    @Override
    public void run(){
        try {
            result = solver.solve();
        } catch (GeometryException e) {
            this.e = e;
        }

        if(action != null)
            action.perform();
    }

    public String getResult()throws GeometryException{
        if(e == null)
            return result;
        throw e;
    }



}
