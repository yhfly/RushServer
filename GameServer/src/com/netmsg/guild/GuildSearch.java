package com.netmsg.guild;

import com.guild.GuildMgr;
import com.netmsg.NetCmd;
import com.netmsg.PBMessage;
import com.pbmessage.GamePBMsg.GuildCommonMsg;
import com.player.GamePlayer;
import com.util.StringUtil;

public class GuildSearch implements NetCmd
{
	public void execute(GamePlayer player, PBMessage packet) throws Exception
	{
		GuildCommonMsg netMsg = GuildCommonMsg.parseFrom(packet.getMsgBody());
		String guildName = netMsg.getGuildName();
		if (StringUtil.isNotNull(guildName))
		{
			GuildMgr.getInstance().searchGuildName(guildName, player);
		}
	}
}