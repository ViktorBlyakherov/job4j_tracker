package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User rslUser = null;
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                rslUser = user;
                break;
            }
        }
        if (rslUser == null) {
            throw new UserNotFoundException("User not found!");
        }
        return rslUser;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() < 3) {
            throw new UserInvalidException("Invalid user");
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            validate(user);
        } catch (UserInvalidException ui) {
            System.out.println(ui.getMessage());
        } catch (UserNotFoundException unf) {
            System.out.println(unf.getMessage());
        }
    }
}
