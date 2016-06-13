function check()
{
	var category=document.addCategoryForm.category;
	var newCategory=document.addCategoryForm.newCategory;

	if(newCategory.value=="")
	{
		alert("新增类别不能为空！");
		return(false);
	}
	else
	{
		for(var i=0;i<category.length;i++)
		{
			if(newCategory.value==category[i].value)
			{
				alert("该类别已存在！");
				return(false);
			}
		}
	}

	return(true);
}

