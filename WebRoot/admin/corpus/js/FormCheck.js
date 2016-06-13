function FormCheck()
{
	var category=document.corpusUploadForm.category;
	var language=document.corpusUploadForm.language;
	var year=document.corpusUploadForm.year;
	var month=document.corpusUploadForm.month;
	var day=document.corpusUploadForm.day;
	var origin=document.corpusUploadForm.origin; 
	var net=document.corpusUploadForm.net; 
	var paper=document.corpusUploadForm.paper; 
	var title=document.corpusUploadForm.title;
	var author=document.corpusUploadForm.author;
	var attachment=document.corpusUploadForm.attachment;
	var flag=0;
	if(0<document.corpusUploadForm.hour<=8)
		var hour=8;
	else if (8<document.corpusUploadForm.hour<=16)
		var hour=16;
	else
		var hour=24;
	/*
	for(var i=0;i<category.length;i++)
	{
		if(category[i].selected)
		{
			if(category[i].value=="----")
			{
				alert("Please input Category!");
				return(false);
			}
		}
	}*/
	
	for(var i=0;i<language.length;i++)
	{
		if(language[i].selected)
		{
			if(language[i].value=="----")
			{
				alert("请填写语言种类！");
				return(false);
			}
		}
	}

	for(var i=0;i<year.length;i++)
	{
		if(year[i].selected)
		{
			if(year[i].value=="----")
			{
				alert("请填写日期！");
				return(false);
			}
			flag=1;
		}
	}
	
	for(var i=0;i<month.length;i++)
	{
		if(month[i].selected)
		{
			if(month[i].value=="----")
			{
				alert("请填写日期！");
				return(false);
			}
		}
	}
	
	for(var i=0;i<day.length;i++)
	{
		if(day[i].selected)
		{
			if(day[i].value=="----")
			{
				alert("请填写日期！");
				return(false);
			}
		}
	}
	
	if(title.value=="")
	{
		alert("请填写标题！"); 
		return(false);
	} 
	
	if(author.value=="")
	{
		alert("请填写作者！"); 
		return(false);
	} 

	
	return(true);
}