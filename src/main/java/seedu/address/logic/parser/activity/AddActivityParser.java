package seedu.address.logic.parser.activity;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACTIVITY_TITLE;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.activity.AddActivityCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.listmanagers.activity.Activity;
import seedu.address.model.listmanagers.activity.Duration;
import seedu.address.model.util.attributes.Location;
import seedu.address.model.util.attributes.Title;
import seedu.address.model.util.attributes.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddActivityParser implements Parser<AddActivityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddActivityCommand
     * and returns an AddActivityCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */

    public AddActivityCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ACTIVITY_TITLE,
                        PREFIX_ACTIVITY_DURATION, PREFIX_ACTIVITY_LOCATION, PREFIX_ACTIVITY_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_ACTIVITY_TITLE, PREFIX_ACTIVITY_DURATION, PREFIX_ACTIVITY_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddActivityCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_ACTIVITY_TITLE).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_ACTIVITY_DURATION).get());
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_ACTIVITY_LOCATION).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_ACTIVITY_TAG));


        Activity activity = new Activity(title, duration, location, tagList, Optional.empty());

        return new AddActivityCommand(activity);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}