package pooTV;

public class Error implements Command {
    private Actions actions;

    public Error(Actions actions) {
        this.actions = actions;
    }

    @Override
    public void execute() {
        actions.error();
    }
}
