package filter;

import domain.Appointment;

//checks if the hour of the appointment is between 2 values
public class FilterAppointmentsByHour implements AbstractFilter<Appointment>{
    private String hourBegin;
    private String hourEnd;
    public FilterAppointmentsByHour(String hourBegin, String hourEnd){
        this.hourEnd=hourEnd;
        this.hourBegin=hourBegin;
    }

    @Override
    public boolean accept(Appointment entity) {
        return entity.getHour().compareTo(hourEnd)<=0 && entity.getHour().compareTo(hourBegin)>=0; // hour string less than limitUp and greater that limitDown
    }
}

