function FormCheck()
{
	var category=document.corpusSearchForm.category;
	var language=document.corpusSearchForm.language;
	var year=document.corpusSearchForm.year;
	var month=document.corpusSearchForm.month;
	var day=document.corpusSearchForm.day;
	var year2=document.corpusSearchForm.year2;
	var month2=document.corpusSearchForm.month2;
	var day2=document.corpusSearchForm.day2;
	var origin=document.corpusSearchForm.origin; 
	var net=document.corpusSearchForm.net; 
	var paper=document.corpusSearchForm.paper; 
	var title=document.corpusSearchForm.title;
	var author=document.corpusSearchForm.author;
	if(0<document.corpusUploadForm.hour<=8)
		var hour=8;
	else if (8<document.corpusUploadForm.hour<=16)
		var hour=16;
	else
		var hour=24;
	var flag=0;
	var yFlag=0;
	var yFlag2=0;
		
	for(var i=0;i<category.length;i++)
	{
		if(category[i].selected)
		{
			if(category[i].value!="-1")
			{
				flag=1;
			}
		}
	}
	
	for(var i=0;i<language.length;i++)
	{
		if(language[i].selected)
		{
			if(language[i].value!="----")
			{
				flag=1;
			}
		}
	}
	
	for(var i=0;i<year.length;i++)
	{
		if(year[i].selected)
		{
			if(year[i].value!="----")
			{
				flag=1;
				yFlag=1;
			}
		}
	}
	for(var i=0;i<month.length;i++)
	{
		if(month[i].selected)
		{
			if(month[i].value!="----" && yFlag==0)
			{
				flag=1;
				alert("请选择开始年份。");
				return(false);
			}
		}
	}
	for(var i=0;i<day.length;i++)
	{
		if(day[i].selected)
		{	
			if(day[i].value!="----" && yFlag==0)
			{
				flag=1;
				alert("请选择开始年份。");
				return(false);
			}
		}
	}
	
	
	
	
	for(var i=0;i<year2.length;i++)
	{
		if(year2[i].selected)
		{
			if(year2[i].value!="----")
			{
				flag=1;
				yFlag2=1;
			}
		}
	}
	for(var i=0;i<month2.length;i++)
	{
		if(month2[i].selected)
		{
			if(month2[i].value!="----" && yFlag2==0)
			{
				flag=1;
				alert("请选择截止年份。");
				return(false);
			}
		}
	}
	for(var i=0;i<day2.length;i++)
	{
		if(day2[i].selected)
		{	
			if(day2[i].value!="----" && yFlag2==0)
			{
				flag=1;
				alert("请选择截至年份。");
				return(false);
			}
		}
	}
	
	var icon=0;
	for(var i=0;i<origin.length;i++)
	{
		if(origin[i].checked)
		{
			icon=1;
			if(origin[i].value=="网络媒体")
			{
				for(var i=0;i<net.length;i++)
				{
					if(net[i].selected)
					{
						if(net[i].value=="----")
						{
							alert("请选择网络媒体类别。");
							return(false);
						}
					}
				}
			} 
			else if(origin[i].value=="纸质媒体")
			{
				for(var i=0;i<paper.length;i++)
				{
					if(paper[i].selected)
					{
						if(paper[i].value=="----")
						{
							alert("请选择纸质媒体类别。");
							return(false);
						}
					}
				}
			}
		}
	}
	if(icon==1)
	{
		flag=1;
	}
	
	if(title.value=="" && author.value=="" && flag==0)
	{
		alert("请至少选择一种检索条件！"); 
		return(false);
	}
	
	return(true);
}
