package com.example.moviedemo.run;

import com.example.moviedemo.bean.Business;
import com.example.moviedemo.bean.Customer;
import com.example.moviedemo.bean.Movie;
import com.example.moviedemo.bean.User;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * @program: movieDemo
 * @description:
 * @Creator: 阿昇
 * @CreateTime: 2023-05-07 18:48
 * @LastEditTime: 2023-05-07 18:48
 */

public class MovieSystem {
    /**
     * 定义系统的数据容器用户存储数据
     * 1.存储很多用户(客户对象,商家对象)
     *
     * @param args
     */
    public static final List<User> ALL_USERS = new ArrayList<>(); //final不可能更改

    /**
     * 2.存储全部商家和排片信息
     * 商家 = [p1,p2,p3....]
     *
     * @param args
     */
    public static Map<Business, List<Movie>> ALL_MOVIES = new HashMap<>();
    //定义final手打命令
    public static final Scanner SYS_SC = new Scanner(System.in);
    //定义一个静态User类型变量
    public static User loginUser;
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//    public static final Logger LOGGER = (Logger) LoggerFactory.getLogger("MovieSystem");

    /**
     準備一些測試數據
     * @param args
     */
    static {
        Customer c = new Customer();
        c.setLoginName("321");
        c.setPassword("321");
        c.setUserName("陳罐細");
        c.setSex("男");
        c.setPhone("03985421313");
        c.setAccountMoney(1000000);
        ALL_USERS.add(c);
        Customer c1 = new Customer();
        c.setLoginName("f2123");
        c.setPassword("123456");
        c.setUserName("陳罐細");
        c.setSex("男");
        c.setPhone("03985421313");
        c.setAccountMoney(1000000);
        ALL_USERS.add(c1);
        Business b = new Business();
        b.setLoginName("123");
        b.setPassword("123");
        b.setUserName("嘰嘰歪歪");
        b.setAddress("新北市");
        b.setShopName("黑马影城");
        b.setAccountMoney(0);
        ALL_USERS.add(b);
        List<Movie> movies = new ArrayList<>();//加個movies集合
        ALL_MOVIES.put(b, movies);//b =[2,3...]

        Business b2 = new Business();
        b2.setLoginName("c1234646");
        b2.setPassword("1324646");
        b2.setUserName("嘰嘰歪歪1");
        b2.setAddress("新北市");
        b2.setShopName("阿昇影城");
        b2.setAccountMoney(0);
        ALL_USERS.add(b2);
        //商家一锭要加入店铺接片信息中去
        List<Movie> movies1 = new ArrayList<>();
        ALL_MOVIES.put(b2, movies1);
    }

    public static void main(String[] args) {
        showMain();
    }

    //**首页
    private static void showMain() {
        while (true) {
            System.out.println("=====asheng电影院首页");
            System.out.println("1.登陆");
            System.out.println("2.用户注册");
            System.out.println("3.商家注册");
            System.out.println("输入操作命令");
            String command = SYS_SC.nextLine();
            switch (command) {
                case "1":
                    login();
                    break;
                case "2":

                    break;
                case "3":
                    break;
                default:
                    System.out.println("命令有误");

            }
        }
    }

    /**
     * 登录功能
     */
    private static void login() {
        while (true) {
            System.out.println("输入名称");
            String userName = SYS_SC.nextLine();
            System.out.println("输入密码");
            String passWord = SYS_SC.nextLine();

            //1.根据登陆名称查询用户对象
            User u = getUserByLoginName(userName);
            loginUser = u;//记住登陆成功的用户 --------------------------------------------------------------
//            LOGGER.info(u.getUserName()+"登入了系统");


            //2.判断用户对象是否存在
            if (u != null) {
                //3.比对密码
                if (u.getPassword().equals(passWord)) {
                    //成功..
                    //判断是用户登录,还是商家登陆
                    if (u instanceof Customer) {
                        //当前是用户
                        System.out.println(u.getUserName() + "登入了系统");
                        showCustomerMain();
                    } else {
                        //是商家
                        System.out.println(u.getUserName() + "登入了系统");
                        showBusinessMain();
                    }
                    System.out.println(u.getUserName() + "登入了系统");
                    return;//退出后把login结束掉
                } else {
                    System.out.println("密码错误");

                }
            } else {
                System.out.println("登录名称有勿,请确认");

            }
//
        }

    }

    /**
     * 商家操作界面
     */
    private static void showBusinessMain() {
        while (true) {
            System.out.println("========商家界面==========");
            System.out.println(loginUser.getUserName() + (loginUser.getSex() == "男" ? "先生" : "女士" + "欢迎光临"));
            System.out.println("1.展示详情:");
            System.out.println("2.上架电影:");
            System.out.println("3.下架电影:");
            System.out.println("4.修改电影:");
            System.out.println("5.退出系统");

            System.out.println("选择命令");
            String command = SYS_SC.nextLine();
            switch (command) {
                case "1":
                    //展示
                    showBusinessInfo();
                    break;
                case "2":
                    try {
                        addMovies();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    deleteMovies();
                    break;
                case "4":
                    updateMovies();
                    break;
                case "5":
                    System.out.println("退回登陆画面");
                    return;
                default:
                    System.out.println("输入有误重新输入");
                    break;
            }
        }
    }

    /**
     * 更新电影
     */
    private static void updateMovies() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>修改电影<<<<<<<<<<<<<<<<<<");
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIES.get(business);
        if (movies.size() == 0) {
            System.out.println("无片可以修改");
            return;
        }
        //2.让用户选下架电影的名城
        while (true) {
            System.out.println("请您输入修改电影的名称");
            String movieName = SYS_SC.nextLine();

            //3.查电影影片对象
            Movie movie = getMovieByname(movieName);
            if (movie != null) {
                //修改
                System.out.println("输入修改后片名");
                String name = SYS_SC.nextLine();
                System.out.println("输入修改后主演");
                String actor = SYS_SC.nextLine();
                System.out.println("输入修改后电影时长");
                String time = SYS_SC.nextLine();
                System.out.println("输入修改后票价");
                String price = SYS_SC.nextLine();
                System.out.println("输入修改后票数");
                String totalnumber = SYS_SC.nextLine();
                while (true) {
                    try {
                        System.out.println("请输入修改后影片的放映时间:按着yyyy/MM/dd HH:mm:ss \\n EX: 2023/03/03 00:00:00");
                        String stime = SYS_SC.nextLine();
                        movie.setActor(actor);
                        movie.setName(name);
                        movie.setTime(Double.valueOf(time));
                        movie.setPrice(Double.valueOf(price));
                        movie.setNumber(Integer.valueOf(totalnumber));
                        movie.setStarttime(sdf.parse(stime));
                        System.out.println("恭喜已成功修改该影片");
                        showBusinessInfo();
                        return;//退出该方法
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("时间出问题");
                    }
                }
            } else {
                System.out.println("该店铺没有该影片");
                System.out.println("请问继续修改妈?y/n");
                String command = SYS_SC.nextLine();
                switch (command) {
                    case "y":
                        break;//继续,再进入while回圈
                    default:
                        System.out.println("OK已退出修改功能");
                        return;
                }
            }
        }
    }

    /**
     * 删除电影(下架)
     */
    private static void deleteMovies() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>下架电影<<<<<<<<<<<<<<<<<<");
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIES.get(business);
        if (movies.size() == 0) {
            System.out.println("无电影可以下架");
            return;
        }
        //2.让用户选下架电影的名城
        while (true) {
            System.out.println("请您输入下架电影的名称");
            String movieName = SYS_SC.nextLine();

            //3.查电影影片对象
            Movie movie = getMovieByname(movieName);
            if (movie != null) {
                //下架
                movies.remove(movie);
                System.out.println("当前电影成功下架:" + movie.getName());
                showBusinessInfo();
                return;
            } else {
                System.out.println("该店铺没有该影片");
                System.out.println("请问继续下架妈?y/n");
                String command = SYS_SC.nextLine();
                switch (command) {
                    case "y":
                        break;//继续,再进入while回圈
                    default:
                        System.out.println("OK已退出下架功能");
                        return;
                }
            }
        }
    }

    public static Movie getMovieByname(String nameMovie) {
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIES.get(business);
        for (Movie movie : movies) {
            if (movie.getName().contains(nameMovie)) {

                return movie;
            }
        }
        return null;
    }

    /**
     * 商架电影上架:创建一个电影对象,存入到商家的集合去
     */
    private static void addMovies() throws ParseException {
        System.out.println("============上架电影=========");
        Business business = (Business) loginUser;
        List<Movie> movies = ALL_MOVIES.get(business);
        System.out.println("输入片名");
        String name = SYS_SC.nextLine();
        System.out.println("输入主演");
        String actor = SYS_SC.nextLine();
        System.out.println("输入电影时长");
        String time = SYS_SC.nextLine();
        System.out.println("输入票价");
        String price = SYS_SC.nextLine();
        System.out.println("输入票数");
        String totalnumber = SYS_SC.nextLine();
        while (true) {
            System.out.println("请输入影片的放映时间: EX: 2023/03/03 00:00:00");
            String stime = SYS_SC.nextLine();
            // public Movie(String name, String actor, double time, double price, int number, Date starttime)
            try {
                Movie movie = new Movie(name, actor, Double.valueOf(time), Double.valueOf(price), Integer.valueOf(totalnumber), sdf.parse(stime));
                movies.add(movie);
                System.out.println("已成功上架{" + movie.getName() + "}");
                return;//退出
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("时间出问题");
            }
        }
    }

    /**
     * 展是商家的信息排片情况
     */
    private static void showBusinessInfo() {
        System.out.println("商家详情界面");
        System.out.println(loginUser.getUserName() + "商家,正在看自己的祥情~");
        //根据伤价对象,昨为Map集合的键,提取对应的值就是其接片信息
        Business business = (Business) loginUser;
        System.out.println(("店名:" + business.getShopName() + "\t\t地址:" + business.getAddress()));
        List<Movie> movies = ALL_MOVIES.get(loginUser);
        System.out.println("片名\t\t主演\t\t评分\t\t票价\t\t余票数量\t\t放映时间");
        if (movies.size() > 0) {
            for (Movie movie : movies) {
                System.out.println(movie.getName() + "\t\t" + movie.getActor() + "\t\t" + movie.getScore()
                        + "\t\t" + movie.getPrice() + "\t\t" + movie.getScore() + "\t\t" + movie.getNumber() + "\t\t" + sdf.format(movie.getStarttime()));
            }
        } else {
            System.out.println("当前无片上映");

        }
    }

    /**
     * 客户操作界面
     */
    private static void showCustomerMain() {
        while (true) {
            System.out.println("===========客户界面============");
            System.out.println(loginUser.getUserName() + (loginUser.getSex() == "男" ? "先生" : "女士" + "欢迎光临"));
            System.out.println("选择操作的功能");
            System.out.println("1.展示所有影片信息功能:");
            System.out.println("2.根据电影名称查询电影信息:");
            System.out.println("3.评分功能:");
            System.out.println("4.购票功能:");
            System.out.println("5.退出系统");
            System.out.println("选择命令");
            String command = SYS_SC.nextLine();
            switch (command) {
                case "1":
                    //展示所有电影
                    showAllMovies();
                    break;
                case "2":
                    System.out.println("请输入您要查询的电影名称：");
                    String movieName = SYS_SC.nextLine();
                    getMovieByName(movieName);
                    break;
                case "3":
                    addMovieScore();
                    break;
                case "4":
                    //购票功能
                    buyTicket();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("输入有误重新输入");
            }
        }
    }

    /**
     * TODO..
     *
     * @param movieName
     * @return
     */
    private static void getMovieByName(String movieName) {
//        Business business = (Business) loginUser;
//        List<Movie> movies = ALL_MOVIES.get(business);
        ALL_MOVIES.forEach((business, movies) -> {//business对象 , List的movie
            System.out.println(("店名:" + business.getShopName() + "\t\t地址:" + business.getAddress()));
            for (Movie movie : movies) {
                if (movie.getName().equals(movieName)) {
                    System.out.println("电影名称：" + movie.getName());
                    System.out.println("电影时长：" + movie.getTime() + "分钟");
                    System.out.println("电影评分：" + movie.getScore());
                    System.out.println("电影票价：" + movie.getPrice() + "元");
                    return;
                } else {
                    System.out.println("未找到该电影！是否退出y/n");
                    String command = SYS_SC.nextLine();
                    switch (command) {
                        case "y":
                            break;
                        default:
                            System.out.println("好的,退出");
                            return;
                    }
                }
            }
        });

    }

    /**
     * 给电影评分
     * TODO*
     */
    private static void addMovieScore() {
        showAllMovies();
        System.out.println("请输入电影名称：");
        String movieName = SYS_SC.nextLine();
        ALL_MOVIES.forEach((business, movies) -> {
            for (Movie movie : movies) {
                if (movie.getName().equals(movieName)) {
                    System.out.println("请输入评分（1-5之间的整数）：");
                    int score = SYS_SC.nextInt();
                    if (score >= 1 && score <= 5) {
                        movie.setScore(score);
                        System.out.println("评分添加成功！");
                        return;
                    } else {
                        System.out.println("评分无效，请输入1-5之间的整数！");
                        String command = SYS_SC.nextLine();
                        switch (command) {
                            case "y":
                                break;
                            default:
                                System.out.println("好的,退出");
                                return;
                        }
                    }
                }else{
                    System.out.println("未找到该电影！是否退出y/n");
                    String command = SYS_SC.nextLine();
                    switch (command) {
                        case "y":
                            break;
                        default:
                            System.out.println("好的,退出");
                            return;
                    }

                }
            }

        });

    }

    /**
     * 买票功能:
     * 1.用户选择需要购买的票的商家和电影信息
     * 2.可以选择购买的数量
     * 3.购买成功后需要支付金额,并更新商家金额和客户金额
     */
    private static void buyTicket() {

        showAllMovies();//先展示
        System.out.println("-------------用户购票功能----------");
        while (true) {
            System.out.println("请您输入需要买票的门店");
            String shopName = SYS_SC.nextLine();
            //查询是否存在该商家
            Business business = getUserByShopName(shopName);//找商家对象
            if (business == null) {
                System.out.println("对不起,没有该店铺");
            } else {
                //2.商家全部的电影
                List<Movie> movies = ALL_MOVIES.get(business);
                //3.判断电影是否上映
                if (movies.size() > 0) {
                    //4.开始选片购买
                    while (true) {
                        System.out.println("---请输入需要购买的电影片称:");
                        String movieName = SYS_SC.nextLine();
                        //去当前商家下,查询该电影对象
                        Movie movie = getMovieByShopAndName(business, movieName);
                        if (movie != null) {
                            //开始购买
                            while (true) {
                                System.out.println("请输入电影票数");
                                String number = SYS_SC.nextLine();
                                int buyNumber = Integer.valueOf(number);
                                //判断可以是否购票
                                if (movie.getNumber() >= buyNumber) {
                                    //可以购买
                                    //当前需要花费的金额
                                    double money = BigDecimal.valueOf(movie.getPrice()).multiply(BigDecimal.valueOf(buyNumber))
                                            .doubleValue();  //使用BigDecimal精准算出电影金额乘以电影票数
                                    if (loginUser.getAccountMoney() >= money) {
                                        //可以买票了
                                        System.out.println("您成功购买了" + movie.getName() + buyNumber + "张票! 总金额:" + money);
                                        //更新自己和商家的金额
                                        loginUser.setAccountMoney(loginUser.getAccountMoney() - money);
                                        business.setAccountMoney(business.getAccountMoney() + money);
                                        movie.setNumber(movie.getNumber() - buyNumber);
                                        return;//结束
                                    } else {
                                        System.out.println("钱不够是否继续");
                                        System.out.println("是否继续购票y/n");
                                        String command = SYS_SC.nextLine();
                                        switch (command) {
                                            case "y":
                                                break;
                                            default:
                                                System.out.println("好的,退出");
                                                return;
                                        }
                                    }
                                } else {
                                    //票数不够
                                    System.out.println("您当前最多可以购买" + movie.getNumber());
                                    System.out.println("是否继续买票?y/n");
                                    String command = SYS_SC.nextLine();
                                    switch (command) {
                                        case "y":
                                            break;
                                        default:
                                            System.out.println("好的!");
                                            return;

                                    }
                                }
                            }
                        } else {
                            System.out.println("电影名称有毛病");
                        }
                    }

                } else {
                    System.out.println("该电影关门了");
                    System.out.println("是否进续买票?y/n");
                    String command = SYS_SC.nextLine();
                    switch (command) {
                        case "y":
                            break;//继续,再进入while回圈
                        default:
                            System.out.println("好的");
                            return;
                    }
                }
            }
        }
    }

    public static Movie getMovieByShopAndName(Business business, String Name) {
        List<Movie> movies = ALL_MOVIES.get(business);
        for (Movie movie : movies) {
            if (movie.getName().contains(Name)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * * 根据商家名称查询商家对象
     *
     * @return
     */
    public static Business getUserByShopName(String shopName) {
        Set<Business> businesses = ALL_MOVIES.keySet();
        for (Business business : businesses) {
            if (business.getShopName().equals(shopName)) {
                return business;
            }
        }
        return null;
    }

    /**
     * 展示全部上家和排片信息 (遍历全部商家和排片信息并展示)
     */
    private static void showAllMovies() {
        System.out.println("---------展示全部商家和电影");
        ALL_MOVIES.forEach((business, movies) -> {//business对象 , List的movie
            System.out.println(("店名:" + business.getShopName() + "\t\t地址:" + business.getAddress()));
            System.out.println("\t\t片名\t\t主演\t\t评分\t\t票价\t\t余票数量\t\t放映时间");
            for (Movie movie : movies) {
                System.out.println("\t\t" + movie.getName() + "\t\t" + movie.getActor() + "\t\t" + movie.getScore()
                        + "\t\t" + movie.getPrice() + "\t\t" + movie.getScore() + "\t\t" + movie.getNumber() + "\t\t" + sdf.format(movie.getStarttime()));
            }

        });
    }

    public static User getUserByLoginName(String userName) {
        for (User user : ALL_USERS) { //ALL_USERS 初始化就给加了一些会员
            if (userName.equals(user.getLoginName())) {
                return user;
            }
        }
        return null;//查无用户名
    }
}
