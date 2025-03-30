package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.AuthenticateCommand.MESSAGE_FAILURE;
import static seedu.address.logic.commands.AuthenticateCommand.MESSAGE_SUCCESS;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.exceptions.AuthenticateException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.CreateUserException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Account;
import seedu.address.model.ReadOnlyAccountBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;



public class AuthenticateCommandTest {

    private Logic logic = new LogicStub();

    @Test
    public void execute_validCredentials_success() {
        assertEquals(MESSAGE_SUCCESS, new AuthenticateCommand("Admin", "Admin@123").authenticateUser(logic));
    }

    @Test
    public void invalidUsername_throwsAuthenticationException() {
        String reply;
        try {
            reply = new AuthenticateCommand("Admin22", "Admin@123").authenticateUser(logic);
        } catch (AuthenticateException e) {
            reply = e.getMessage();
        }
        assertEquals(MESSAGE_FAILURE, reply);
    }

    @Test
    public void invalidPassword_throwsAuthenticationException() {
        String reply;
        try {
            reply = new AuthenticateCommand("Admin22", "Admin@1235").authenticateUser(logic);
        } catch (AuthenticateException e) {
            reply = e.getMessage();
        }
        assertEquals(MESSAGE_FAILURE, reply);
    }

    @Test
    public void nullCredentials_throwsAuthenticationException() {
        String reply;
        try {
            reply = new AuthenticateCommand(null, null).authenticateUser(logic);
        } catch (AuthenticateException e) {
            reply = e.getMessage();
        }
        assertEquals(MESSAGE_FAILURE, reply);
    }

    /**
     * A logicStub that returns the admin account
     */
    private class LogicStub implements Logic {
        @Override
        public CommandResult execute(String commandText) throws CommandException, ParseException {
            return null;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return null;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return null;
        }

        @Override
        public Path getAddressBookFilePath() {
            return null;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }

        @Override
        public void logUserIn() {

        }

        @Override
        public ReadOnlyAccountBook getAccountBook() {
            return null;
        }

        @Override
        public Path getAccountBookFilePath() {
            return null;
        }

        @Override
        public ArrayList<Account> getFilteredAccountList() {
            return null;
        }

        @Override
        public String addNewUser(Account account) throws CreateUserException, IOException {
            return "";
        }

        @Override
        public ArrayList<Account> getAccountList() {
            ArrayList<Account> accounts = new ArrayList<>();
            accounts.add(new Account("ITstaff", "ITstaff@123"));
            return accounts;
        }
    }
}
