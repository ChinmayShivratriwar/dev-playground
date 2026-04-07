package com.chinmayshivratriwar.dev_playground.dsa.greedy;

import java.util.ArrayList;
import java.util.List;

public class ActivitySelection {
    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1, 3));
        activities.add(new Activity(2, 5));
        activities.add(new Activity(4, 7));
        activities.add(new Activity(1, 8));
        activities.add(new Activity(5, 9));
        System.out.println(maxActivity(activities));
    }

    public static int maxActivity(List<Activity> activities){
        activities.sort((activity1, activity2) -> activity1.finish - activity2.finish);
        int res = 1;
        int prev = 0;
        for(int curr = 1; curr < activities.size(); curr++){
            if(activities.get(curr).start >= activities.get(prev).finish){
                res++;
                prev = curr;
            }
        }
        return res;
    }
}

class Activity {
    int start, finish;

    public Activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
    }
}