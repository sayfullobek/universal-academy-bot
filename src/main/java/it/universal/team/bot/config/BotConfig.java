package it.universal.team.bot.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BotConfig {

    List<String> inline = Arrays.asList("quzi", "gaday", "mirshod", "zohid");


    Map<String, String> language = new HashMap<>();
}
