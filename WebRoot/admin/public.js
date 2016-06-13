////////////////////////////////公共事件类模块///////////////////////////////////////
function clsPublicFunction()
{
	this.getDom	    = clsPublicFunction$getDom;
//	this.insertRow	= clsPublicFunction$insertRow;
	this.getDBDom	= clsPublicFunction$getDBDom;
	this.addoOption	= clsPublicFunction$addoOption;
	this.deleteRow	= clsPublicFunction$deleteRow;
	this.emptyObject= clsPublicFunction$emptyObject;
	this.getRow		= clsPublicFunction$getRow;
	this.DomToString= clsPublicFunction$DomToString;
	this.getNamedObj= clsPublicFunction$getNamedObj;
	this.setObjectDisabled = clsPublicFunction$setObjectDisabled;	
}
//设置Disabled属性
function clsPublicFunction$setObjectDisabled()
{
	//TEXT RADIO PASSWORD HIDDEN CHECKBOX 
	var objList = document.getElementsByTagName("INPUT");
	for (var i=0;i<objList.length;i++){
		if (objList[i].type.toUpperCase()!="BUTTON") {
			if (objList[i].getAttribute("prikey")!=null) {
				//dynamicSelect or  dynamicSelectTree
				objList[i].onfocus = "";
			}
			objList[i].setAttribute("readOnly",true);
			objList[i].disabled = true;
		}else{
				if (objList[i].value=="增行" || objList[i].value=="删行") {
					objList[i].disabled = true;
				}
		}
	}
	
	var objList = document.getElementsByTagName("SELECT");
	for (var i=0;i<objList.length;i++){		 
			objList[i].disabled = true;		 
	}
	
	var objList = document.getElementsByTagName("TEXTAREA");
	for (var i=0;i<objList.length;i++){		 
			objList[i].disabled = true;		 
	}
}
//通过路径或者字符串得到xml对象
function clsPublicFunction$getDom(strPath,strValue)
{
	//alert(strValue);
	var oDom	= new ActiveXObject("MSXML2.DOMDocument.5.0");
	oDom.async	= false;
	if (strPath != null)
	{
		
	    return 	oDom.load(strPath) ? oDom : null;
	}
	else
	{
	    return	oDom.loadXML(strValue) ? oDom : null;
	}
}
//得到数据库中的查询结果（xml对象）
function clsPublicFunction$getDBDom(strSQL)
{
	return xmlSend("../asp/getData.asp", "<a>"+ strSQL +"</a>");
}
//增加一个option对象
function clsPublicFunction$addoOption(oSelectCtrl,strValue,strText,selValue,selText,strAttributeNameArray,strAttributeValueArray)
{
	var oOption = document.createElement("OPTION");
	if (oSelectCtrl != null)
	{
		if (strValue != null)
			oOption.value	= strValue;
		if (strText	!= null)
			oOption.text	= strText;
		if ((strValue == selValue && strValue != null && selValue != null) || (strText == selText && strText != null && selText != null))
			oOption.selected = true;

		if (strAttributeNameArray != null)
		{
			for (var nI=0; nI<strAttributeNameArray.length; nI++)
				oOption.setAttribute(strAttributeNameArray[nI],strAttributeValueArray[nI]);
		}
		oSelectCtrl.add(oOption);
	}
}
//删除表格中的行
function clsPublicFunction$deleteRow(oTable)
{
	for (var nI=oTable.rows.length - 1; nI>=0; nI--)
	{
		if (oTable.rows[nI].style.display == "block")
			oTable.deleteRow(nI);
	}
}
//清空对象内容
function clsPublicFunction$emptyObject(obj)
{
	switch(obj.tagName.toUpperCase())
	{
		case "INPUT":
			switch(obj.type.toUpperCase())
			{
				case "TEXT": case "PASSWORD": case "HIDDEN":
					 obj.value = "";
					 break;
				case "RADIO": case "CHECKBOX":
					 obj.checked = false;
					 break;
				default:
					obj.value = "";
					break;
			}
			break;
		case "LABEL":
			obj.innerText = "";
			break;
		case "SELECT":
			obj.innerHTML = "";
			break;
		case "TABLE":
			this.deleteRow(obj);
			break;
		case "TEXTAREA":
			obj.value = "";
			break;
		default:
			obj.innerText = "";
			break;
	}
}

//得到行对象
function clsPublicFunction$getRow()
{
	var element = event.srcElement;
	if (element.tagName != null)
	{
		while (element.tagName.toUpperCase() != "TR")
		{
			element = element.parentNode;
			if (element == null)
				break;
		}
		return element;
	}
	return null;
}

//得到指定对象//2006/08修改
function clsPublicFunction$getNamedObj(strTagName)
{
	var element = event.srcElement;
	if (element.tagName != null)
	{
		while (element.tagName.toUpperCase() != strTagName)
		{
			element = element.parentNode;
			if (element == null)
				break;
			if(element.tagName.toUpperCase()=="HTML")
			{
				element=null;
				break;
			}
		}
		return element;
	}
	return null;
}

//去掉Dom对象中的"<"和">"符号
function clsPublicFunction$DomToString(strValue)
{
	return (strValue != null) ? strValue.replace(/\</g,"&lt;").replace(/\>/g,"&gt;") : null;
}

var oPublicFunction = new clsPublicFunction();

////////////////////////////////组合xml对象类模块/////////////////////////////////////
function clsComboXML()
{
	this.Dom		= null;
	this.createDom	= clsComboXML$createDom;
	this.createNode	= clsComboNode$createNode;
	this.appendNode	= clsComboXML$appendNode;
}
//其中nameArray为属性名称、valueArray为属性值
function clsComboXML$createDom(strTagName,strText,nameArray,valueArray)
{
	var oDom	= new ActiveXObject("MSXML2.DOMDocument");
	oDom.async	= false;
	oDom.loadXML("<"+ strTagName +" />");
	if (nameArray != null)
	{
		for (var nI=0; nI<nameArray.length; nI++)
		{
			oDom.documentElement.setAttribute(nameArray[nI],valueArray[nI]);
		}
	}
	if (strText != null)
		oDom.documentElement.text = strText;
	this.Dom = oDom;
	
}

//注意：oDom必须有appendChild属性，此事件也可以为一个节点添加一个子节点 2
function clsComboXML$appendNode(oDom,oNode)
{
	oDom.appendChild(oNode);
}

//创建一个节点
function clsComboNode$createNode(oDom,strTagName,strText,nameArray,valueArray)
{
	var oNode	= this.Dom.createElement(strTagName);
	if (nameArray != null)
	{
		for (var nI=0; nI<nameArray.length; nI++)
		{
			oNode.setAttribute(nameArray[nI],valueArray[nI]);
		}
	}
	if (strText != null)
		oNode.text = strText;
	this.appendNode(oDom,oNode);
	return oNode;
}

//计算控件向上到body的距离
    function calculateCtrlTop(obj, container)
    {
        if (obj != null)
        {
            var nTop = 0;
            while(obj != null)
            {   
                nTop += obj.offsetTop;
                if (container == obj.offsetParent)
                    break;
                else{
					if(obj.offsetParent == null){
						return nTop;
					}
                    nTop -= obj.offsetParent.scrollTop;
				}
                
                obj = obj.offsetParent;
            }
            return nTop;
        }
    }

    //计算控件向左到body的距离
    function calculateCtrlLeft(obj, container)
    {
        if (obj != null)
        {
            var nLeft = 0;
            while(obj != null)
            {
		nLeft += obj.offsetLeft;
                if (container == obj.offsetParent)
                    break;
                else{
					if(obj.offsetParent == null){
						return nLeft;
					}
                    nLeft -= obj.offsetParent.scrollLeft;
				}
                
                obj = obj.offsetParent;

            }
            return nLeft;
        }
    }
    
    