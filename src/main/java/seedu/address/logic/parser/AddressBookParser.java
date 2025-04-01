package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_ACCESS_RIGHTS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterStatusCommand;
import seedu.address.logic.commands.FindByCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.LogOutCommand;
import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.commands.SetStatusCommand;
import seedu.address.logic.commands.exceptions.InvalidAccessRightsException;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    // Commands that make use of one or more prefixes
    public static final String[] COMMAND_WORDS_PREFIXED = {
        AddCommand.COMMAND_WORD,
        EditCommand.COMMAND_WORD,
        FindByCommand.COMMAND_WORD,
        SetStatusCommand.COMMAND_WORD,
        FilterStatusCommand.COMMAND_WORD
    };

    // Commands that take in a single argument without any prefix
    public static final String[] COMMAND_WORDS_SINGLE_ARG = {
        DeleteCommand.COMMAND_WORD,
        ClearCommand.COMMAND_WORD,
        FindCommand.COMMAND_WORD,
        ImportCommand.COMMAND_WORD
    };

    // Commands that do not require any arguments
    public static final String[] COMMAND_WORDS_STANDALONE = {
        ListCommand.COMMAND_WORD,
        ExitCommand.COMMAND_WORD,
        HelpCommand.COMMAND_WORD,
        LoginCommand.COMMAND_WORD,
        LogOutCommand.COMMAND_WORD,
        RegisterCommand.COMMAND_WORD,
    };

    public static final String[] COMMAND_WORDS_ALL = Stream.of(COMMAND_WORDS_STANDALONE, COMMAND_WORDS_SINGLE_ARG,
        COMMAND_WORDS_PREFIXED).flatMap(Arrays::stream).toArray(String[]::new);

    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     * User has full admin rights and access to all commands.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        // All command words must be added to their corresponding arrays based on the type of command.
        if (!Arrays.asList(COMMAND_WORDS_ALL).contains(commandWord)) {
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindByCommand.COMMAND_WORD:
            return new FindByCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case LoginCommand.COMMAND_WORD:
            return new LoginCommand();

        case LogOutCommand.COMMAND_WORD:
            return new LogOutCommand();

        case SetStatusCommand.COMMAND_WORD:
            return new SetStatusCommandParser().parse(arguments);

        case FilterStatusCommand.COMMAND_WORD:
            return new FilterStatusCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);

        case RegisterCommand.COMMAND_WORD:
            return new RegisterCommand();

        default:
            throw new AssertionError("This is an illegal state. "
                    + "Invalid command words should have caught earlier, "
                    + "due to their absence in the COMMAND_WORDS_ALL array.");
        }
    }

    /**
     * Parses user input into command for execution.
     * User only has IT Staff Access Rights, some commands are unavailable and throws InvalidAccessRightsException.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommandIT(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:

        case EditCommand.COMMAND_WORD:

        case DeleteCommand.COMMAND_WORD:

        case ClearCommand.COMMAND_WORD:
            logger.finer("This user input caused a InvalidAccessRightsException: " + userInput);
            throw new InvalidAccessRightsException(MESSAGE_INVALID_ACCESS_RIGHTS);

        case FindByCommand.COMMAND_WORD:
            return new FindByCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case LoginCommand.COMMAND_WORD:
            return new LoginCommand();

        case LogOutCommand.COMMAND_WORD:
            return new LogOutCommand();

        case SetStatusCommand.COMMAND_WORD:
            return new SetStatusCommandParser().parse(arguments);

        case FilterStatusCommand.COMMAND_WORD:
            return new FilterStatusCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser().parse(arguments);
        default:
            throw new AssertionError("This is an illegal state. "
                    + "Invalid command words should have caught earlier, "
                    + "due to their absence in the COMMAND_WORDS_ALL array.");
        }
    }

}
