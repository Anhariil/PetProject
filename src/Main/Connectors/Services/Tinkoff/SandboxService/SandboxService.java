package Main.Connectors.Services.Tinkoff.SandboxService;

import Main.Connectors.Services.Tinkoff.Tinkoff;

public class SandboxService extends Tinkoff {
    private static final String name = "SandboxService/";

    @Override
    protected void setUrl(String type) {
        super.setUrl(type);
        this.URl += name;
    }
}
