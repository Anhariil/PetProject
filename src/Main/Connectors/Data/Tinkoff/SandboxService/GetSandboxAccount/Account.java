package Main.Connectors.Data.Tinkoff.SandboxService.GetSandboxAccount;

import Main.DateTime;

public class Account {
    protected String id;
    protected String type;
    protected String name;
    protected String status;
    protected DateTime openDate;
    protected String accessLevel;

    public Account(String id, String type, String name, String status, DateTime openDate, String accessLevel) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
        this.openDate = openDate;
        this.accessLevel = accessLevel;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public DateTime getOpenDate() {
        return openDate;
    }

    public String getAccessLevel() {
        return accessLevel;
    }
}
