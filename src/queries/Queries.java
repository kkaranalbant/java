/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package queries;

import dataaccess.Access;

import characters.Characters;

import java.sql.Statement;

import java.sql.SQLException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import characters.MainCharacter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaan
 */
public class Queries {

    public static Access access = null ;

    private Statement statement = null;

    private PreparedStatement pStatement = null;

    public boolean addCharacter(Characters character) {

        String query = queryAddingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();

            statement = null;

            return false;

        }

        return true;

    }

    public boolean chooseCharacter(int id) {

        String query = characterChoosingQuery(id);

        boolean value = false;

        try {

            pStatement = access.getConnection().prepareStatement(query);
            ResultSet result = pStatement.executeQuery();
            MainCharacter mc = new MainCharacter();
            value = mc.setMainCharacterFromDb(result);
            result = null;
            mc = null;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return value;

    }

    public boolean chooseCharacter(String name) {

        String query = characterChoosingQuery(name);

        boolean value = false;

        try {

            pStatement = access.getConnection().prepareStatement(query);
            ResultSet result = pStatement.executeQuery();
            MainCharacter mc = new MainCharacter();
            value = mc.setMainCharacterFromDb(result);
            mc = null;
            result = null;
        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return value;

    }

    private String queryAddingCreator(Characters character) {

        String query = "";

        if (character.getGun() == null && character.getArmor() == null) {
            query = "insert into characters (id,name,damage,health,money,defaultHp,defaultDamage,defence,defaultDefence,lev,exp,maxExp,charType) values (" + character.getId() + ",'"
                    + character.getName()
                    + "'," + character.getDamage() + ","
                    + character.getHp() + ","
                    + character.getMoney() + ","
                    + character.getDefaultHp() + ","
                    + character.getDefaultDamage()
                    + "," + character.getDefence()
                    + "," + character.getDefaultDefence()
                    + "," + character.getLevel().getLevel()
                    + "," + character.getExperience()
                    + "," + character.getMaxExp()
                    + ",'" + character.toString() + "')";

        }
        return query;

    }

    public void setDamage(Characters character, float damage) {

        String query = queryDamageUpdatingCreator(character, damage);

        try {

            pStatement = access.getConnection().prepareCall(query);

            pStatement.executeUpdate();

            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();

            pStatement = null;

        }

    }

    public void setGunId(Characters character) {

        String query = queryGunIdUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);

            pStatement.executeUpdate();

            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();

            pStatement = null;

        }

    }

    public void setArmorId(Characters character) {

        String query = queryArmorIdUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);

            pStatement.executeUpdate();

            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();

            pStatement = null;

        }

    }

    public void setDefence(Characters character, float defence) {

        String query = queryDefenceUpdatingCreator(character, defence);

        try {

            pStatement = access.getConnection().prepareStatement(query);

            pStatement.executeUpdate();

            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();

            pStatement = null;

        }

    }

    public void setExp(Characters character, int xp) {

        String query = queryExpUpdatingCreator(character, xp);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }
    }

    public void setLevel(Characters character) {

        String query = queryLevelUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

    }

    public void setMaxExp(Characters character) {

        String query = queryMaxExpUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

    }

    public void setPerk(Characters character) {

        String query = queryPerkUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }
    }

    public void setHp(Characters character) {

        String query = queryHpUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

    }

    public void setMoney(Characters character) {

        String query = queryUpdatingGold(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

    }

    public void setDefaultHp(Characters character) {

        String query = queryDefaultHpUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

    }

    public void setDefaultDamage(Characters character) {

        String query = queryDefaultDamageUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

    }

    public void setDefaultDefence(Characters character) {

        String query = queryDefaultDefenceUpdatingCreator(character);

        try {

            pStatement = access.getConnection().prepareStatement(query);
            pStatement.executeUpdate();
            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

    }

    private String queryPerkUpdatingCreator(Characters character) {

        return "update characters set perk = " + character.getLevel().getPerk() + " where id =" + character.getId() + " ;";

    }

    private String queryDamageUpdatingCreator(Characters character, float damage) {

        return "update characters set damage = " + damage + " where id = " + character.getId() + " ;";

    }

    private String queryDefenceUpdatingCreator(Characters character, float defence) {

        return "update characters set defence = " + defence + " where id = " + character.getId() + " ;";

    }

    private String queryGunIdUpdatingCreator(Characters character) {

        return "update characters set gunId = " + character.getGun().getId() + " where id = " + character.getId();

    }

    private String queryArmorIdUpdatingCreator(Characters character) {

        return "update characters set armorId = " + character.getArmor().getId() + " where id = " + character.getId();

    }

    private String queryExpUpdatingCreator(Characters character, int xp) {

        return "update characters set exp = " + xp + " where id = " + character.getId() + " ;";

    }

    private String queryLevelUpdatingCreator(Characters character) {

        return "update characters set lev = " + character.getLevel().getLevel() + " where id = " + character.getId() + " ;";

    }

    private String queryMaxExpUpdatingCreator(Characters character) {

        return "update characters set maxExp = " + character.getMaxExp() + " where id = " + character.getId() + " ;";

    }

    private String queryHpUpdatingCreator(Characters character) {

        return "update characters set health = " + character.getHp() + " where id = " + character.getId() + " ;";

    }

    private String queryUpdatingGold(Characters character) {

        return "update characters set money = " + character.getMoney() + " where id = " + character.getId() + " ;";

    }

    private String characterChoosingQuery(int id) {

        return "select * from characters where id = " + id + " ;";

    }

    private String characterChoosingQuery(String name) {

        return "select * from characters where name = '" + name + "' ;";

    }

    private String queryDefaultHpUpdatingCreator(Characters character) {

        return "update characters set defaultHp = " + character.getDefaultHp() + " where id = " + character.getId() + " ;";

    }

    private String queryDefaultDamageUpdatingCreator(Characters character) {

        return "update characters set defaultDamage = " + character.getDefaultDamage() + " where id = " + character.getId() + " ;";

    }

    private String queryDefaultDefenceUpdatingCreator(Characters character) {

        return "update characters set defaulDefence = " + character.getDefaultDefence() + " where id = " + character.getId() + " ;";

    }

    public List<Integer> getAllId() {

        String query = "select id from characters ;";

        List<Integer> resultList = new ArrayList();

        try {

            pStatement = access.getConnection().prepareStatement(query);

            ResultSet resultSet = pStatement.executeQuery();

            pStatement = null;

            while (resultSet.next()) {

                resultList.add(resultSet.getInt("id"));

            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

        return resultList;

    }

    public List<String> getAllNames() {

        String query = "select name from characters ;";

        List<String> result = new ArrayList();

        try {

            pStatement = access.getConnection().prepareStatement(query);
            ResultSet results = pStatement.executeQuery();

            while (results.next()) {

                result.add(results.getString("name"));

            }

            pStatement = null;

        } catch (SQLException ex) {

            ex.printStackTrace();
            pStatement = null;

        }

        return result;

    }

}
