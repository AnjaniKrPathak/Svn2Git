

/*new menu code start */

function changeHMenu(id)
{
	
	menuHIndex=id;
var menuId="menu" + id;
var submenuId="submenu"+id;
document.getElementById('menu1').style.backgroundColor="#F7F7F7";
document.getElementById('menu2').style.backgroundColor="#F7F7F7";
document.getElementById('menu3').style.backgroundColor="#F7F7F7";

document.getElementById('submenu1').style.display="none";
document.getElementById('submenu2').style.display="none";
document.getElementById('submenu3').style.display="none";

 
document.getElementById(submenuId).style.display="block";
document.getElementById(menuId).style.backgroundColor="#53B4E6";


if(id==2)
 changeHSubMenu(1);
else if(id==3)
{
   changeHSubMenu(11);
}
}

function funHHomeSelected(id)
{
var menuId="menu" + id;
document.getElementById('menu1').style.backgroundColor="#F7F7F7";
document.getElementById('menu2').style.backgroundColor="#F7F7F7";
document.getElementById('menu3').style.backgroundColor="#F7F7F7";

document.getElementById(menuId).style.backgroundColor="#53B4E6";


document.getElementById(menuId).style.borderColor="#53B4E6";
}

function hideHHomesubM(id)
{
var menuId="menu" + menuHIndex;
document.getElementById('menu1').style.backgroundColor="#F7F7F7";
document.getElementById('menu2').style.backgroundColor="#F7F7F7";
document.getElementById('menu3').style.backgroundColor="#F7F7F7";
document.getElementById(menuId).style.backgroundColor="#53B4E6";

document.getElementById('menu1').style.borderColor="#CCCCCC";
document.getElementById('menu2').style.borderColor="#CCCCCC";
document.getElementById('menu3').style.borderColor="#CCCCCC";

document.getElementById(menuId).style.backgroundColor="#53B4E6";
}


function changeHSubMenu(id)
{
var itemId="item"+id;

document.getElementById('item1').style.color="#006";
document.getElementById('item2').style.color="#006";
document.getElementById('item3').style.color="#006";
document.getElementById('item4').style.color="#006";
document.getElementById('item5').style.color="#006";
document.getElementById('item6').style.color="#006";
document.getElementById('item7').style.color="#006";
document.getElementById('item8').style.color="#006";
document.getElementById('item9').style.color="#006";
document.getElementById('item10').style.color="#006";
document.getElementById('item11').style.color="#006";
document.getElementById('item12').style.color="#006";
document.getElementById('item13').style.color="#006";
document.getElementById(itemId).style.color="#FFFFFF";

document.getElementById('item1').style.fontWeight="normal";
document.getElementById('item2').style.fontWeight="normal";
document.getElementById('item3').style.fontWeight="normal";
document.getElementById('item4').style.fontWeight="normal";
document.getElementById('item5').style.fontWeight="normal";
document.getElementById('item6').style.fontWeight="normal";
document.getElementById('item7').style.fontWeight="normal";
document.getElementById('item8').style.fontWeight="normal";
document.getElementById('item9').style.fontWeight="normal";
document.getElementById('item10').style.fontWeight="normal";
document.getElementById('item11').style.fontWeight="normal";
document.getElementById('item12').style.fontWeight="normal";
document.getElementById('item13').style.fontWeight="normal";
document.getElementById(itemId).style.fontWeight="bold";
}




/*new menu code end */
/*JS Page for menu */


function eprescrib()
{
	document.getElementById('menu1').style.backgroundColor="#F7F7F7";
	document.getElementById('menu2').style.backgroundColor="#F7F7F7";
	document.getElementById('menu3').style.backgroundColor="#F7F7F7";
	document.getElementById('menu4').style.backgroundColor="#F7F7F7";
	document.getElementById('menu5').style.backgroundColor="#F7F7F7";
	document.getElementById('menu6').style.backgroundColor="#53B4E6";
}
var mastertabvar=new Object();
mastertabvar.baseopacity=0;
mastertabvar.browserdetect="";

	function showsubM(id)
{
	var subid='submenu'+id;
	document.getElementById('submenu1').style.display="none";
	document.getElementById('submenu2').style.display="none";
	document.getElementById('submenu3').style.display="none";
	document.getElementById('submenu4').style.display="none";
	document.getElementById('submenu5').style.display="none";
	document.getElementById('submenu6').style.display="none";
	document.getElementById(subid).style.display="block";
	/*funSelected(id);*/
	}
	
function showHsubM(id)
{
	var subid='submenu'+id;
	document.getElementById('submenu1').style.display="none";
	document.getElementById('submenu2').style.display="none";
	document.getElementById('submenu3').style.display="none";
	document.getElementById(subid).style.display="block";

	}
	


function hidesubM()
{
	var id=document.getElementById('txtTabIndex').value;

var subid='submenu'+id;	
	var mid='menu'+id;
	document.getElementById('submenu1').style.display="none";
	document.getElementById('submenu2').style.display="none";
	document.getElementById('submenu3').style.display="none";
	document.getElementById('submenu4').style.display="none";
	document.getElementById('submenu5').style.display="none";
	document.getElementById('submenu6').style.display="none";
	document.getElementById('submenu7').style.display="none";
	document.getElementById('submenu8').style.display="none";
	document.getElementById('submenu9').style.display="none";
	document.getElementById('submenu10').style.display="none";
	document.getElementById('submenu11').style.display="none";
	document.getElementById(subid).style.display="block";
	
	document.getElementById('menu1').style.backgroundColor="#F7F7F7";
	document.getElementById('menu2').style.backgroundColor="#F7F7F7";
	document.getElementById('menu3').style.backgroundColor="#F7F7F7";
	document.getElementById('menu4').style.backgroundColor="#F7F7F7";
	document.getElementById('menu5').style.backgroundColor="#F7F7F7";
	document.getElementById('menu6').style.backgroundColor="#F7F7F7";
	document.getElementById('menu7').style.backgroundColor="#F7F7F7";
	document.getElementById('menu8').style.backgroundColor="#F7F7F7";
	document.getElementById('menu9').style.backgroundColor="#F7F7F7";
	document.getElementById('menu10').style.backgroundColor="#F7F7F7";
	document.getElementById('menu11').style.backgroundColor="#F7F7F7";
	document.getElementById('menu13').style.backgroundColor="#F7F7F7";
	
	document.getElementById(mid).style.backgroundColor="#53B4E6";
	 document.getElementById(subid).style.display="block";
	 
	/*if(id=!6)
		{
		document.getElementById('submenu6').style.display="none";
		}*/
	
}


function hideHsubM()
{
	var id=document.getElementById('txtMTabIndex').value;
/*	alert(id);*/
	var subid='submenu'+id;
	var mid='menu'+id;
	document.getElementById('menu1').style.backgroundColor="#F7F7F7";
	document.getElementById('menu2').style.backgroundColor="#F7F7F7";
	document.getElementById('menu3').style.backgroundColor="#F7F7F7";
	document.getElementById('menu4').style.backgroundColor="#F7F7F7";
	document.getElementById(mid).style.backgroundColor="#53B4E6";
	 document.getElementById(subid).style.display="block";
	 document.getElementById('submenu6').style.display="none";
	}

	function funSelected(id)
	{
		//var tid=document.getElementById('txtTabIndex').value;
		var subid='menu'+id;
		 
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";	
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById('menu4').style.backgroundColor="#F7F7F7";
		document.getElementById('menu5').style.backgroundColor="#F7F7F7";
		document.getElementById('menu6').style.backgroundColor="#F7F7F7";
		document.getElementById('menu7').style.backgroundColor="#F7F7F7";
		document.getElementById('menu8').style.backgroundColor="#F7F7F7";
		document.getElementById('menu9').style.backgroundColor="#F7F7F7";
		document.getElementById('menu10').style.backgroundColor="#F7F7F7";
		document.getElementById('menu11').style.backgroundColor="#F7F7F7";
		document.getElementById('menu13').style.backgroundColor="#F7F7F7";
		
		document.getElementById(subid).style.backgroundColor="#53B4E6";
		
		
}
	
	
	function funHSelected(id)
	{
		var tid=document.getElementById('txtMTabIndex').value;
		var subid='menu'+id;
		var showid='submenu'+tid;
		
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById('menu4').style.backgroundColor="#F7F7F7";
		document.getElementById(subid).style.backgroundColor="#53B4E6";
		
		document.getElementById('submenu1').style.display="none";
		document.getElementById('submenu2').style.display="none";
		document.getElementById('submenu3').style.display="none";
		document.getElementById('submenu4').style.display="none";
		document.getElementById(showid).style.display="block";
		
	}
	
	function changeIndex()
	{	
		var id=document.getElementById('txtTabIndex').value;	
		var indexId='menu'+id;
		var subid='submenu'+id;
		
		var submenuid =document.getElementById('txtSubMenuIndex').value;
		var sindexId='doctorMenuForm:item'+submenuid;
		
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById('menu4').style.backgroundColor="#F7F7F7";
		document.getElementById('menu5').style.backgroundColor="#F7F7F7";
		document.getElementById('menu6').style.backgroundColor="#F7F7F7";
		document.getElementById('menu7').style.backgroundColor="#F7F7F7";
		document.getElementById('menu8').style.backgroundColor="#F7F7F7";
		document.getElementById('menu9').style.backgroundColor="#F7F7F7";
		document.getElementById('menu10').style.backgroundColor="#F7F7F7";
		document.getElementById('menu11').style.backgroundColor="#F7F7F7";
		document.getElementById('menu13').style.backgroundColor="#F7F7F7";
		document.getElementById(indexId).style.backgroundColor="#53B4E6";
	/*	alert(id);
		for(var j=1;j<=10;i++)
		{
		  var menuidvar='submenu'+j;
		  document.getElementById(menuidvar).style.display="none";
		  if(j==id)
			  {
			  document.getElementById(subid).style.display="block";
			  }
		}*/
		document.getElementById('submenu1').style.display="none";
		document.getElementById('submenu2').style.display="none";
		document.getElementById('submenu3').style.display="none";
		document.getElementById('submenu4').style.display="none";
		document.getElementById('submenu5').style.display="none";
		document.getElementById('submenu6').style.display="none";
		document.getElementById('submenu7').style.display="none";
		document.getElementById('submenu8').style.display="none";
		document.getElementById('submenu9').style.display="none";
		document.getElementById('submenu10').style.display="none";
		document.getElementById('submenu11').style.display="none";
		document.getElementById('submenu13').style.display="none";
		
		document.getElementById(subid).style.display="block";
		
		for(var i=1;i<=21;i++)
			{
			  var menuidvar='doctorMenuForm:item'+i;
			  document.getElementById(menuidvar).style.color="#006";
			  if(i==submenuid)
				  {
				  document.getElementById(sindexId).style.color="#FFFFFF";
				  }
			}
		/*document.getElementById(submenuid).style.backgroundColor="#F7F7F7";*/
		
	}
	
	
	function changeHIndex()
	{
		var id=document.getElementById('txtMTabIndex').value;	
		var indexId='menu'+id;
		var subid='submenu'+id;
	
		var submenuid =document.getElementById('txtMSubMenuIndex').value;//document.getElementById('txtMSubMenuIndex').value;
		
		var sindexId='HomeMenuForm:sitem'+submenuid;
		/*alert(indexId);*/
		document.getElementById('menu1').style.backgroundColor="#F7F7F7";
		document.getElementById('menu2').style.backgroundColor="#F7F7F7";
		document.getElementById('menu3').style.backgroundColor="#F7F7F7";
		document.getElementById('menu4').style.backgroundColor="#F7F7F7";
		document.getElementById(indexId).style.backgroundColor="#53B4E6";

		document.getElementById('submenu1').style.display="none";
		document.getElementById('submenu2').style.display="none";
		document.getElementById('submenu3').style.display="none";
		document.getElementById('submenu4').style.display="none";
		document.getElementById(subid).style.display="block";
		
		for(var i=1;i<=13;i++)
			{
			  var menuidvar='HomeMenuForm:sitem'+i;
			  document.getElementById(menuidvar).style.color="#006";
			  if(i==submenuid)
				  {
				  
				  document.getElementById(sindexId).style.color="#FFFFFF";
				  }
			}
		
		
		
		
	}
	
	function changeBorderColor()
	{
		
		document.getElementById('menu1').style.borderBottomColor="#53B4E6";
		document.getElementById('menu2').style.borderBottomColor="#53B4E6";
		document.getElementById('menu3').style.borderBottomColor="#53B4E6";
		document.getElementById('menu4').style.borderBottomColor="#53B4E6";
		document.getElementById('menu5').style.borderBottomColor="#53B4E6";
		document.getElementById('menu6').style.borderBottomColor="#53B4E6";
	}
	
	/* Menu code for home */

	
		
		
		
		function changeHBorderColor()
		{
			
			document.getElementById('menu1').style.borderBottomColor="#53B4E6";
			document.getElementById('menu2').style.borderBottomColor="#53B4E6";
			document.getElementById('menu3').style.borderBottomColor="#53B4E6";
		}
		
		
	function changeLeftMenuIndex(screen)
	{
	
	var screenName=screen;
			
		if(screenName=='med')
			{
			var sId=document.getElementById('txtSTabIndex').value;			
			var subId='lefpmenu'+sId;
			
			document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu3').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu4').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu5').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu6').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu7').style.backgroundColor="#F7F7F7";
			document.getElementById(subId).style.backgroundColor="#53B4E6";
			
			document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu3').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu4').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu5').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu6').style.borderColor="#CCCCCC";
			document.getElementById(subId).style.borderColor="#53B4E6";
			
			}
		else if(screenName=='clinic')
			{
			var smId=document.getElementById('txtCTabIndex').value;			
			var subId='lefpmenu'+smId;
		
			document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";
			document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";
			/*document.getElementById('lefpmenu3').style.borderColor="#F7F7F7";
			document.getElementById('lefpmenu4').style.borderColor="#F7F7F7";
			document.getElementById('lefpmenu5').style.borderColor="#F7F7F7";*/
			document.getElementById(subId).style.backgroundColor="#53B4E6";
			
			document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";
			/*document.getElementById('lefpmenu3').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu4').style.borderColor="#CCCCCC";
			document.getElementById('lefpmenu5').style.borderColor="#CCCCCC";*/
			document.getElementById(subId).style.borderColor="#53B4E6";
			
			}
		else if(screenName=='home')
		{
		var hsmId=document.getElementById('txtHSMTabIndex').value;			
		var subId='lefpmenu'+hsmId;
		
		document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";
		document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";
		document.getElementById('lefpmenu3').style.backgroundColor="#F7F7F7";
		document.getElementById(subId).style.backgroundColor="#53B4E6";
		
		/*document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";
		document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";
		document.getElementById('lefpmenu3').style.borderColor="#CCCCCC";*/
		document.getElementById(subId).style.borderColor="#53B4E6";
		
		}
		
		else if(screenName=='vsscreen')
		{
		var hsmId=document.getElementById('vsTabIndex1').value;			
		var subId='lefpmenu'+hsmId;
		
		/*document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";*/
		document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";
		/*document.getElementById(subId).style.backgroundColor="#53B4E6";*/
		
		/*document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";*/
		document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";
		/*document.getElementById(subId).style.borderColor="#53B4E6";*/
		
		}
		else if(screenName=='vsscreen1')
		{
		var hsmId=document.getElementById('vsTabIndex').value;			
		var subId='lefpmenu'+hsmId;
		
		document.getElementById('lefpmenu1').style.backgroundColor="#F7F7F7";
		document.getElementById('lefpmenu2').style.backgroundColor="#F7F7F7";
		document.getElementById(subId).style.backgroundColor="#53B4E6";
		
		/*document.getElementById('lefpmenu1').style.borderColor="#CCCCCC";
		document.getElementById('lefpmenu2').style.borderColor="#CCCCCC";*/
		document.getElementById(subId).style.borderColor="#53B4E6";
		
		}
	}
	
	
	
	
function showsubmenu(masterid, id){
if (typeof highlighting!="undefined")
clearInterval(highlighting)
submenuobject=document.getElementById(id)
mastertabvar.browserdetect=submenuobject.filters? "ie" : typeof submenuobject.style.MozOpacity=="string"? "mozilla" : ""
hidesubmenus(mastertabvar[masterid])
submenuobject.style.display="block"
instantset(mastertabvar.baseopacity)
highlighting=setInterval("gradualfade(submenuobject)",50)
}

function hidesubmenus(submenuarray){
for (var i=0; i<submenuarray.length; i++)
document.getElementById(submenuarray[i]).style.display="none"
}

function instantset(degree){
if (mastertabvar.browserdetect=="mozilla")
submenuobject.style.MozOpacity=degree/100
else if (mastertabvar.browserdetect=="ie")
submenuobject.filters.alpha.opacity=degree
}


function gradualfade(cur2){
if (mastertabvar.browserdetect=="mozilla" && cur2.style.MozOpacity<1)
cur2.style.MozOpacity=Math.min(parseFloat(cur2.style.MozOpacity)+0.1, 0.99)
else if (mastertabvar.browserdetect=="ie" && cur2.filters.alpha.opacity<100)
cur2.filters.alpha.opacity+=10
else if (typeof highlighting!="undefined") //fading animation over
clearInterval(highlighting)
}

function initalizetab(tabid)
{

mastertabvar[tabid]=new Array();
var menuitems=document.getElementById(tabid).getElementsByTagName("li");

for (var i=0; i<menuitems.length; i++)
 {
	if (menuitems[i].getAttribute("rel"))
	 {
		menuitems[i].setAttribute("rev", tabid); //associate this submenu with main tab
		mastertabvar[tabid][mastertabvar[tabid].length]=menuitems[i].getAttribute("rel"); //store ids of submenus of tab menu
	
		if (menuitems[i].className=="selected")
			showsubmenu(tabid, menuitems[i].getAttribute("rel"));
			menuitems[i].getElementsByTagName("a")[0].onmouseover=function(){
				showsubmenu(this.parentNode.getAttribute("rev"), this.parentNode.getAttribute("rel"));
			}
	 }
 	}
}