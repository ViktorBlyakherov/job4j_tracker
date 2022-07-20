package ru.job4j.tracker;

import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StartUITest {

    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(out),
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new EditAction(out),
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(out),
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenEditItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        UserAction[] actions = new UserAction[]{
                new EditAction(out),
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit" + ln
                        + "=== Edit item ===" + ln
                        + "Item was edited successfully" + ln
                        + "Menu:" + ln
                        + "0. Edit item" + ln
                        + "1. Exit" + ln
                        + "Exiting" + ln
        ));
    }

    @Test
    public void whenShowAllTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        Item two = tracker.add(new Item("test2"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss", Locale.ENGLISH);
        String oneCreated = one.getCreated().format(formatter);
        String twoCreated = one.getCreated().format(formatter);
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        UserAction[] actions = new UserAction[]{
                new ShowAllAction(out),
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Show all item" + ln
                        + "1. Exit" + ln
                        + "=== Show all items ===" + ln
                        + "Item{id=" + one.getId() + ", name='" + one.getName() + "', "
                        + "created=" + oneCreated + "}" + ln
                        + "Item{id=" + two.getId() + ", name='" + two.getName() + "', "
                        + "created=" + twoCreated + "}" + ln
                        + "Menu:" + ln
                        + "0. Show all item" + ln
                        + "1. Exit" + ln
                        + "Exiting" + ln
        ));
    }

    @Test
    public void whenFindByIdTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss", Locale.ENGLISH);
        String oneCreated = one.getCreated().format(formatter);
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        UserAction[] actions = new UserAction[]{
                new FindByIdAction(out),
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find by id" + ln
                        + "1. Exit" + ln
                        + "=== Find item by id ===" + ln
                        + "Item{id=" + one.getId() + ", name='" + one.getName() + "', "
                        + "created=" + oneCreated + "}" + ln
                        + "Menu:" + ln
                        + "0. Find by id" + ln
                        + "1. Exit" + ln
                        + "Exiting" + ln
        ));
    }

    @Test
    public void whenFindByNameTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss", Locale.ENGLISH);
        String oneCreated = one.getCreated().format(formatter);
        Input in = new StubInput(
                new String[] {"0", "test1", "1"}
        );
        UserAction[] actions = new UserAction[]{
                new FindByNameAction(out),
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                "Menu:" + ln
                        + "0. Find by name" + ln
                        + "1. Exit" + ln
                        + "=== Find items by name ===" + ln
                        + "Item{id=" + one.getId() + ", name='" + one.getName() + "', "
                        + "created=" + oneCreated + "}" + ln
                        + "Menu:" + ln
                        + "0. Find by name" + ln
                        + "1. Exit" + ln
                        + "Exiting" + ln
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"10", "0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = new UserAction[]{
                new ExitAction(out)
        };
        new StartUI(out).init(in, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(
                        "Menu:" + ln
                                + "0. Exit" + ln
                                + "Wrong input, you can select: 0 .. 0" + ln
                                + "Menu:" + ln
                                + "0. Exit" + ln
                                + "Exiting" + ln
                )
        );
    }
}