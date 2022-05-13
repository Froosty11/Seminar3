package se.kth.salessystem.main.model;

/**
 * Theoretical class checking for identification in case of id-specific discount
 */
public class CustomerIdentification {
    /**
     * No point in having this, since we're never checking discounts etc.
     * Would add if we need alternative flows.
     * @param id id of person/
     * @return true if person is real or whatever
     */
    public boolean checkID(int id) {
        return true;
    }

}
