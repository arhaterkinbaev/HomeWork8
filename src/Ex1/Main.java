package Ex1;

import java.util.ArrayList;
import java.util.List;

class Light {
    public void turnOn() {
        System.out.println("Свет включен.");
    }

    public void turnOff() {
        System.out.println("Свет выключен.");
    }
}

class Door {
    public void open() {
        System.out.println("Дверь открыта.");
    }

    public void close() {
        System.out.println("Дверь закрыта.");
    }
}

class Thermostat {
    private int temperature;

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        System.out.println("Температура установлена на " + temperature + " градусов.");
    }
}

class LightOnCommand implements ICommand {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements ICommand {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

class DoorOpenCommand implements ICommand {
    private Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.open();
    }

    @Override
    public void undo() {
        door.close();
    }
}

class DoorCloseCommand implements ICommand {
    private Door door;

    public DoorCloseCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.close();
    }

    @Override
    public void undo() {
        door.open();
    }
}

class ThermostatSetCommand implements ICommand {
    private Thermostat thermostat;
    private int temperature;

    public ThermostatSetCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        thermostat.setTemperature(temperature);
    }

    @Override
    public void undo() {
        thermostat.setTemperature(20);
    }
}

class Invoker {
    private List<ICommand> commandHistory = new ArrayList<>();

    public void executeCommand(ICommand command) {
        command.execute();
        commandHistory.add(command);
    }

    public void undoCommand() {
        if (commandHistory.isEmpty()) {
            System.out.println("Нет команд для отмены.");
            return;
        }
        ICommand lastCommand = commandHistory.remove(commandHistory.size() - 1);
        lastCommand.undo();
    }
}

class TV {
    public void turnOn() {
        System.out.println("Телевизор включен.");
    }

    public void turnOff() {
        System.out.println("Телевизор выключен.");
    }
}

class Alarm {
    public void activate() {
        System.out.println("Сигнализация активирована.");
    }

    public void deactivate() {
        System.out.println("Сигнализация деактивирована.");
    }
}

class TVOnCommand implements ICommand {
    private TV tv;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }

    @Override
    public void undo() {
        tv.turnOff();
    }
}

class TVOffCommand implements ICommand {
    private TV tv;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }

    @Override
    public void undo() {
        tv.turnOn();
    }
}

class AlarmActivateCommand implements ICommand {
    private Alarm alarm;

    public AlarmActivateCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.activate();
    }

    @Override
    public void undo() {
        alarm.deactivate();
    }
}

class AlarmDeactivateCommand implements ICommand {
    private Alarm alarm;

    public AlarmDeactivateCommand(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.deactivate();
    }

    @Override
    public void undo() {
        alarm.activate();
    }
}

public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();
        TV tv = new TV();
        Alarm alarm = new Alarm();

        Invoker invoker = new Invoker();

        ICommand lightOn = new LightOnCommand(light);
        ICommand lightOff = new LightOffCommand(light);
        ICommand doorOpen = new DoorOpenCommand(door);
        ICommand doorClose = new DoorCloseCommand(door);
        ICommand setTemp = new ThermostatSetCommand(thermostat, 22);
        ICommand tvOn = new TVOnCommand(tv);
        ICommand tvOff = new TVOffCommand(tv);
        ICommand alarmActivate = new AlarmActivateCommand(alarm);
        ICommand alarmDeactivate = new AlarmDeactivateCommand(alarm);

        invoker.executeCommand(lightOn);
        invoker.executeCommand(doorOpen);
        invoker.executeCommand(setTemp);
        invoker.executeCommand(tvOn);
        invoker.executeCommand(alarmActivate);

        invoker.undoCommand();
        invoker.undoCommand();
        invoker.undoCommand();
        invoker.undoCommand();
        invoker.undoCommand();

        invoker.executeCommand(lightOff);
        invoker.executeCommand(doorClose);
        invoker.undoCommand();
    }
}
