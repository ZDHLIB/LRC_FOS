function check()
{
	var paperMedia=document.addPaperMediaForm.paperMedia;
	var newPaperMedia=document.addPaperMediaForm.newPaperMedia;

	if(newPaperMedia.value=="")
	{
		alert("����ֽ��ý�������Ϊ�գ�");
		return(false);
	}
	else
	{
		for(var i=0;i<paperMedia.length;i++)
		{
			if(newPaperMedia.value==paperMedia[i].value)
			{
				alert("��ֽ��ý������Ѵ��ڣ�");
				return(false);
			}
		}
	}

	return(true);
}

