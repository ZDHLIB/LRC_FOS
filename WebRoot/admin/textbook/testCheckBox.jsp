
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>复选框选中效果</title>
<style type="text/css">
.ct{
text-align:center;
}
</style>
<script type="text/javascript">
function allCheck(){
    var obj=document.getElementsByTagName("input");
    if(document.getElementById("all").checked==true){
        for(var i=0;i<obj.length;i++){
            obj[i].checked=true;
        }
    }else{
        for(var i=0;i<obj.length;i++){
            obj[i].checked=false;
        }
    }
}
function checkT_F(){
    var obj=document.getElementsByTagName("input");
    var j=0;
    for(var i=0;i<obj.length;i++){
        if(obj[i].id!='all'){    //如果是复选框
            if(obj[i].checked==true){    //并且为选中
                j++;
            }
        }
    }
    if(j==(obj.length-1)){    //如果复选框选中的数量等于（复选框总和减去全选这个选框的数量）
        document.getElementById("all").checked=true; //全选被激活
    }else{
        document.getElementById("all").checked=false;    //取消全选
    }
}
</script>
</head>
<body>
<table width="500" border="1" cellspacing="0" cellpadding="0" class="ct" onclick="javascript:checkT_F()">
  <tr>
    <td><input type="checkbox" id="all" name="allCK" onclick="javascript:allCheck()"/>全选</td>
    <td>产品名称</td>
    <td>价格(元)</td>
    <td>数量</td>
  </tr>
  <tr>
    <td><input type="checkbox" id="1" /></td>
    <td>诺基亚N85手机</td>
    <td>2589</td>
    <td>6</td>
  </tr>
  <tr>
    <td><input type="checkbox" id="2" /></td>
    <td>佳能数码相机</td>
    <td>1850</td>
    <td>5</td>
  </tr>
  <tr>
    <td><input type="checkbox" id="3" /></td>
    <td>戴尔键盘</td>
    <td>1834</td>
    <td>4</td>
  </tr>
  <tr>
    <td><input type="checkbox" id="4" /></td>
    <td>华为手机</td>
    <td>3432</td>
    <td>3</td>
  </tr>
  <tr>
    <td><input type="checkbox" id="5" /></td>
    <td>iphone</td>
    <td>4000</td>
    <td>20000</td>
  </tr>
  <tr>
    <td><input type="checkbox" id="6" /></td>
    <td>华硕笔记本</td>
    <td>6988</td>
    <td>5</td>
  </tr>

  <tr align="left">
    <td colspan="4">&nbsp;&nbsp;删除选中的产品</td>
  </tr>
</table>

</body>
</html> 