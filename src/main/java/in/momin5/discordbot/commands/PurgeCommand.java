package in.momin5.discordbot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.internal.utils.PermissionUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PurgeCommand  {

    public static void onMessageReceived(MessageReceivedEvent event) {

        if (PermissionUtil.checkPermission(event.getMember(), Permission.MESSAGE_MANAGE, Permission.ADMINISTRATOR)) {

            var message = event.getMessage();
            var channel = event.getChannel();

            try {
                var s = message.getContentRaw().split(" ");
                var purgeAmount = s[1];

                channel.purgeMessages(channel.getHistory().retrievePast(Integer.parseInt(purgeAmount)).complete());

            } catch (ArrayIndexOutOfBoundsException exception) {
                message.reply("Please specify a number of messages to purge!").queue();
            } catch (NumberFormatException exception) {
                message.reply("Thats not a valid integer!").queue();
            } catch (InsufficientPermissionException exception) {
                message.reply("Not enough permissions!").queue();
            }
        }
    }
}
