function check()
{
	var paperMedia=document.addPaperMediaForm.paperMedia;
	var newPaperMedia=document.addPaperMediaForm.newPaperMedia;

	if(newPaperMedia.value=="")
	{
		alert("新增纸质媒体类别不能为空！");
		return(false);
	}
	else
	{
		for(var i=0;i<paperMedia.length;i++)
		{
			if(newPaperMedia.value==paperMedia[i].value)
			{
				alert("该纸质媒体类别已存在！");
				return(false);
			}
		}
	}

	return(true);
}

