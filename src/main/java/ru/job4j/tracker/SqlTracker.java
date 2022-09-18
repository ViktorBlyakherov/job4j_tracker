package ru.job4j.tracker;


import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        Timestamp timestampFromLDT = Timestamp.valueOf(item.getCreated());
        String sql = String.format("insert into item (name, created) values ('%s', '%s') returning id;",
                    item.getName(),
                    timestampFromLDT
                );
        try (Statement statement = cn.createStatement()) {
            statement.execute(sql);
            try (ResultSet rs = statement.getResultSet();) {
                rs.next();
                item.setId(rs.getInt("id"));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("update item set name = ? where id = ?")) {
            statement.setString(1, item.getName());
            statement.setInt(2, id);
            rsl = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement statement =
                     cn.prepareStatement("delete from item where id = ?")) {
            statement.setInt(1, id);
            rsl = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();

        try (Statement statement =
                     cn.createStatement()) {
            statement.execute("select * from item");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Item tmp = new Item();
                tmp.setId(resultSet.getInt("id"));
                tmp.setName(resultSet.getString("name"));
                LocalDateTime localDateTime = resultSet.getTimestamp("created").toLocalDateTime();
                tmp.setCreated(localDateTime);
                rsl.add(tmp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        try (Statement statement =
                     cn.createStatement()) {
            statement.execute("select * from item where name like '%" + key + "%';");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Item tmp = new Item();
                tmp.setId(resultSet.getInt("id"));
                tmp.setName(resultSet.getString("name"));
                LocalDateTime localDateTime = resultSet.getTimestamp("created").toLocalDateTime();
                tmp.setCreated(localDateTime);
                rsl.add(tmp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public Item findById(int id) {
        Item rsl = null;
        try (Statement statement =
                     cn.createStatement()) {
            statement.execute("select * from item where id=" + id + ";");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                rsl = new Item();
                rsl.setId(resultSet.getInt("id"));
                rsl.setName(resultSet.getString("name"));
                LocalDateTime localDateTime = resultSet.getTimestamp("created").toLocalDateTime();
                rsl.setCreated(localDateTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
