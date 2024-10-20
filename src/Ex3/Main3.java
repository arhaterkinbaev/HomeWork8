package Ex3;

import java.util.ArrayList;
import java.util.List;

interface IMediator {
    void sendMessage(String message, User sender);
    void addUser(User user);
    void removeUser(User user);
}

class ChatRoom implements IMediator {
    private List<User> users;

    public ChatRoom() {
        users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, User sender) {
        if (!users.contains(sender)) {
            System.out.println("Ошибка: " + sender.getName() + " не в чате и не может отправлять сообщения.");
            return;
        }
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println(user.getName() + " присоединился к чату.");
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
        System.out.println(user.getName() + " покинул чат.");
    }
}

class User {
    private String name;
    private IMediator mediator;

    public User(String name, IMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        System.out.println(name + ": " + message);
        mediator.sendMessage(message, this);
    }

    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }

    public void joinChat() {
        mediator.addUser(this);
    }

    public void leaveChat() {
        mediator.removeUser(this);
    }
}

public class Main3 {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User user1 = new User("Архат", chatRoom);
        User user2 = new User("Аскар", chatRoom);
        User user3 = new User("Рауан", chatRoom);

        user1.joinChat();
        user2.joinChat();
        user3.joinChat();

        user1.sendMessage("Привет всем!");
        user2.sendMessage("Привет, Рауан!");
        user3.sendMessage("Привет, Аскар!");

        user1.leaveChat();
        user2.sendMessage("Аскар ушёл.");
    }
}
