document.writeln("<script src='\/js\/ajax.js'><\/script>");var jieqiUserId=0;var jieqiUserName='';function fixwidth(){var _bqgmb_head=document.getElementById("_bqgmb_head");var _bqgmb_h1=document.getElementById("_bqgmb_h1");_bqgmb_h1.style.width=(_bqgmb_head.scrollWidth-175)+"px";}
function get_user_info(){if(document.cookie.indexOf('jieqiUserInfo')>=0){var jieqiUserInfo=get_cookie_value('jieqiUserInfo');start=0;offset=jieqiUserInfo.indexOf(',',start);while(offset>0){tmpval=jieqiUserInfo.substring(start,offset);tmpidx=tmpval.indexOf('=');if(tmpidx>0){tmpname=tmpval.substring(0,tmpidx);tmpval=tmpval.substring(tmpidx+1,tmpval.length);if(tmpname=='jieqiUserId')jieqiUserId=tmpval;else if(tmpname=='jieqiUserName_un')jieqiUserName=tmpval;}
start=offset+1;if(offset<jieqiUserInfo.length){offset=jieqiUserInfo.indexOf(',',start);if(offset==-1)offset=jieqiUserInfo.length;}else{offset=-1;}}}}
function get_cookie_value(Name){var search=Name+"=";var returnvalue="";if(document.cookie.length>0){offset=document.cookie.indexOf(search);if(offset!=-1){offset+=search.length;end=document.cookie.indexOf(";",offset);if(end==-1)end=document.cookie.length;returnvalue=unescape(document.cookie.substring(offset,end));}}return returnvalue;}
function showlogin(){get_user_info();if(jieqiUserId!=0&&jieqiUserName!=''&&document.cookie.indexOf('PHPSESSID')!=-1){document.write('<div id="login_top"><a class="login_topbtn c_index_login" href="/userinfo.php">会员中心</a></div>');}else{document.write('<div id="login_top"><a class="login_topbtn c_index_login" href="/login.php">登录</a><a href="/register.php" class="login_topbtn c_index_login">注册</a></div>');}}
function login(){uname=document.getElementById("username").value;upass=document.getElementById("userpass").value;doAjax("/login.php?do=submit","usecookie=365000&username="+ uname+"&userpass="+ upass+"","go_login","POST",0);}
function go_login(t){t=t.replace(/\s/g,'');logintips=document.getElementById("logintips");if(t=="nodata"){logintips.innerHTML="请输入帐号和密码";}
if(t=="nameerror"){logintips.innerHTML="用户名含有非法字符！";}
if(t=="nologin"){logintips.innerHTML="帐号或密码错误，登录失败";}
if(t=="yeslogin"){logintips.innerHTML="登录成功，正在跳转！";window.location.href="/userinfo.php";}}
function register(){uname=document.getElementById("regname").value;upass=document.getElementById("regpass").value;uemail=document.getElementById("regemail").value;doAjax("/register.php?do=submit","uname="+ uname+"&upass="+ upass+"&uemail="+ uemail+"","go_register","POST",0);}
function go_register(t){var t=t.replace(/\s/g,'');var tips=document.getElementById("logintips");if(t=="nodata"){tips.innerHTML="以上信息都必须输入";}
if(t=="nameerror"){tips.innerHTML="用户名含有非法字符！";}
if(t=="bigname"){tips.innerHTML="用户名太长或者太短！";}
if(t=="bigpass"){tips.innerHTML="密码太长或者太短！";}
if(t=="bigemail"){tips.innerHTML="邮箱太长！";}
if(t=="emailerror"){tips.innerHTML="邮箱格式错误！";}
if(t=="havename"){tips.innerHTML="用户名已被注册！";}
if(t=="yesregister"){tips.innerHTML="注册成功并已经登录！";window.location.href="/";}}
function show_sj(articleid){get_user_info();if(jieqiUserId==0){document.writeln("<a href='/login.php?jumpurl="+ encodeURIComponent(document.URL)+"' style='color:#fff'>加入书架<\/a>");}else{document.writeln("<a id='shujia' onclick='shujia("+articleid+")' style='color:#fff'>加入书架<\/a>");}}
function show_bq(articleid,chapterid){get_user_info();if(jieqiUserId==0){document.writeln("<a id='shujia' href='\/login.php?jumpurl="+ encodeURIComponent(document.URL)+"' style='color:red'>《&nbsp;加入书签，方便阅读&nbsp;》<\/a>");}else{document.writeln("<a id='shujia' onclick='shuqian("+articleid+","+chapterid+")' style='color:red'>《&nbsp;加入书签，方便阅读&nbsp;》<\/a>");}}
function shujia(bid){doAjax("/addbookcase.php","action=addbookinfo&bid="+ bid,"shujia2","POST",0);}
function shujia2(t){var t=t.replace(/\s/g,'');var tips=document.getElementById("shujia");if(t=="1"){tips.innerHTML="加入书架成功！";}
if(t=="2"){tips.innerHTML="加入书架失败！";}
if(t=="3"){tips.innerHTML="您还没有登录！";}
if(t=="4"){tips.innerHTML="该书已在书架中！";}}
function shuqian(bid,aid){doAjax("/addbookcase.php","action=addbookmark&bid="+ bid+"&aid="+ aid,"shuqian2","POST",0);}
function shuqian2(t){var t=t.replace(/\s/g,'');var tips=document.getElementById("shujia");if(t=="1"){tips.innerHTML="加入书签成功！";}
if(t=="2"){tips.innerHTML="加入书签失败！";}
if(t=="3"){tips.innerHTML="您还没有登录！";}
if(t=="4"){tips.innerHTML="该书签已存在！";}}
function style_head(){}
function style_middle(){}
function style_bottom(){}
function fmt(){}
function tjkk(){document.writeln("<script src=\"http://s4.cnzz.com/z_stat.php?id=1259483294&web_id=1259483294\" language=\"JavaScript\"></script>");(function(){var bp=document.createElement('script');var curProtocol=window.location.protocol.split(':')[0];if(curProtocol==='https'){bp.src='https://zz.bdstatic.com/linksubmit/push.js';}
else{bp.src='http://push.zhanzhang.baidu.com/push.js';}
var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(bp,s);})();(function(){var src=(document.location.protocol=="http:")?"http://js.passport.qihucdn.com/11.0.1.js?94062671792e59848eeebaca67da31bc":"https://jspassport.ssl.qhimg.com/11.0.1.js?94062671792e59848eeebaca67da31bc";document.write('<script src="'+ src+'" id="sozz"><\/script>');})();}