package ru.job4j.stream;

import java.util.List;

public class Analize {
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        info.added = (int) current.stream().dropWhile(previous::contains).count();
        info.deleted = (int) previous.stream().filter(o -> !current.contains(o)).count();
        info.changed = (int) previous.stream().
                filter(current::contains).
                filter(o -> !o.getName().equals(current.get(current.indexOf(o)).getName())).
                count();
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof User)) return false;

            User user = (User) o;

            return getId() == user.getId();
        }

        @Override
        public int hashCode() {
            return getId();
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }
    }

    public static void main(String[] args) {
        User user1 = new User(1, "aaa");
        User user2 = new User(2, "bbb");
        User user3 = new User(10, "aaa1");
        User user4 = new User(4, "ddd");
        User user5 = new User(12, "aaa2");
        List<User> list1 = List.of(user1, user2);
        List<User> list2 = List.of(user3, user4, user5, user3, user4, user5);
        Analize analize = new Analize();
        System.out.println(analize.diff(list1, list2));
    }
}
