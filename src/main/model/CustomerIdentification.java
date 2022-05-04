package main.model;

public class CustomerIdentification {
    /**
     * No point in having this, since we're never checking discounts etc.
     * Would add if we need alternative flows.
     * @param id id of person
     * @return true if person is real or whatever
     */
    public boolean checkID(int id) {
        return true;
    }

}
