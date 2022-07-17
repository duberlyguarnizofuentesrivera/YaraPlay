package view;

import util.Config;

public interface PanelYara {
    Config CONFIG = new Config();

    void createControls();

    void createEvents();

    void readConfig();
}
