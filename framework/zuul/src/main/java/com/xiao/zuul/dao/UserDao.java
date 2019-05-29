package com.xiao.zuul.dao;

import com.xiao.zuul.pojo.MenuInfo;
import com.xiao.zuul.pojo.RoleInfo;
import com.xiao.zuul.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDao {
    private static RoleInfo admin = new RoleInfo(1,"管理员", "usercenter");
    private static RoleInfo developer = new RoleInfo(2,"开发者", "developer");
    private static RoleInfo player = new RoleInfo(3,"普通用户", "user");


    private static MenuInfo menu1 = new MenuInfo(1, "管理员1", "");
    private static MenuInfo menu2 = new MenuInfo(2, "开发者1", "");
    private static MenuInfo menu3 = new MenuInfo(3, "开发者2", "");
    private static MenuInfo menu4 = new MenuInfo(4, "普通用户1", "");
    private static MenuInfo menu5 = new MenuInfo(5, "普通用户2", "");
    private static MenuInfo menu6 = new MenuInfo(6, "普通用户3", "");


    static {
        List<MenuInfo> playermenus = new ArrayList<>();
        List<MenuInfo> developermenus = new ArrayList<>();
        List<MenuInfo> adminmenus = new ArrayList<>();
        playermenus.add(menu6);
        playermenus.add(menu5);
        playermenus.add(menu4);
        player.setMenus(playermenus);
        developermenus.add(menu3);
        developermenus.add(menu2);
        developermenus.addAll(playermenus);
        developer.setMenus(developermenus);
        adminmenus.add(menu1);
        adminmenus.addAll(developermenus);
        admin.setMenus(adminmenus);
    }
    public UserInfo getUserByName(String username){
        UserInfo user = new UserInfo();
        List<RoleInfo> roles = new ArrayList<>();

        if ("usercenter".equals(username)){
            roles.add(admin);
            roles.add(developer);
            roles.add(player);
            return new UserInfo("usercenter", "123456", roles);
        }
        if ("developer".equals(username)){
            roles.add(developer);
            roles.add(player);
            return new UserInfo("developer", "123456", roles);
        }
        if ("player".equals(username)){
            roles.add(player);
            return new UserInfo("player", "123456", roles);
        }
        log.error("用户不存在");
        return null;
    }
}
