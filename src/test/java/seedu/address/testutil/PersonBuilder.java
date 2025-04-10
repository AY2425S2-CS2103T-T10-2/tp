package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.DeviceInfo;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.OrgID;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Status;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ORG_ID = "0000000555";
    public static final String DEFAULT_DEVICE_INFO = "Device123";
    public static final String DEFAULT_STATUS = "none";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private OrgID orgID;
    private DeviceInfo deviceInfo;
    private Status status;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        orgID = new OrgID(DEFAULT_ORG_ID);
        deviceInfo = new DeviceInfo(DEFAULT_DEVICE_INFO);
        status = Status.fromString(DEFAULT_STATUS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        orgID = personToCopy.getOrgID();
        deviceInfo = personToCopy.getDeviceInfo();
        status = personToCopy.getStatus();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code OrgID} of the {@code Person} that we are building.
     */
    public PersonBuilder withOrgID(String orgID) {
        this.orgID = new OrgID(orgID);
        return this;
    }

    /**
     * Sets the {@code DeviceInfo} of the {@code Person} that we are building.
     */
    public PersonBuilder withDeviceInfo(String deviceInfo) {
        this.deviceInfo = new DeviceInfo(deviceInfo);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = Status.fromString(status);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, orgID, deviceInfo, tags, status);
    }

}
