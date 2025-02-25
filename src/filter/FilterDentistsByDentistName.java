package filter;

import domain.Dentist;
public class FilterDentistsByDentistName implements AbstractFilter<Dentist> {

    private String name;
    public FilterDentistsByDentistName(String name) {
        this.name = name;
    }
    @Override
    public boolean accept(Dentist d) {
        return d.getName().equals(name);
    }
}

