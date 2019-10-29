package com.honji.exhibition.admin.controller;


import com.honji.exhibition.admin.entity.User;
import com.honji.exhibition.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  UserController前端控制器
 * </p>
 *
 * @author yao
 * @since 2019-10-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/get")
    public User get(@RequestParam Long id) {
        return userService.getById(id);
    }
/*

    @GetMapping("/list")
    public EasyUIDataGridResult list(@RequestParam int page, @RequestParam int size,
                                     @RequestParam String name) {
        IPage<User> userPage = new Page<>(page, size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return new EasyUIDataGridResult(userService.page(userPage, queryWrapper));

    }


    @PostMapping("/save")
    public boolean save(@ModelAttribute User user) {
        return userService.saveOrUpdate(user);
    }

    @PostMapping("/remove")
    public boolean remove(@RequestParam String[] ids) {
        List<String> resultList = new ArrayList<>(ids.length);
        Collections.addAll(resultList, ids);
        return userService.removeByIds(resultList);

    }

*/

}
