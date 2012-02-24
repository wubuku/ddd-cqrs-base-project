// JavaScript Document
document.onkeydown = checkKeycode
function checkKeycode(e) {
var keycode;
if (window.event) keycode = window.event.keyCode;
else if (e) keycode = e.which;
//alert("keycode: " + keycode);

	if(keycode==113)
	{
		showSC();
	}

	if(keycode==123)
	{
		showAudit(e);
	}
	if(keycode==27)
	{
		hideAudit();
		hideNote();
		hideHelp();
	}
	if(keycode==120)
	{
		showNote();
	}
	if(keycode==121)
	{
		showHelp();
	}
}

function hideAudit()
{
	if(auditBox!="")
	{
		document.getElementById(auditBox.id).style.display="none";
		document.body.removeChild(auditBox);
		auditBox="";
	}
}
function hideNote(){
	if(NoteBox!="")
	{
		document.getElementById(NoteBox.id).style.display="none";
		document.body.removeChild(NoteBox);
		NoteBox="";
	}
}

function hideHelp(){
	if(helpBox)
	{
		document.getElementById(helpBox.id).style.display="none";
		document.body.removeChild(helpBox);
		helpBox="";
	}
}

var NoteBox = "";
var helpBox;
var dynamicNumber=0;


var theSrcElement;
function showNote()
{
	el = event.srcElement;

	if(el.tagName=="INPUT" || el.tagName=="TEXTAREA" || el.tagName=="SELECT")
	{


		theSrcElement=el;
		posLayer = findPos(el);

		d = document.createElement("div");
		d.id = "myNoteBox";
		NoteBox = d;
		d.style.position="absolute";
		d.style.zIndex = "1000";
		d.style.border = "#71706e 1px solid";
		d.style.background = "#ffffcd";
		d.style.padding ="0 5px 0 5px";
		d.style.width = "300px";
		d.style.left = posLayer[0]+2;
		d.style.top = posLayer[1]+21;

		headerDiv = document.createElement("div");
		headerDiv.innerHTML = "<div style='font-size:14px;padding:0px;background:#ffffcd;color:#000'><table width='100%'><tr><Td><img src='../../images/notes_icon.gif' align='bottom'> Notes </td><td align='right'> &nbsp; <a href='javascript:void(0);' onclick='hideNote()'><img src='../../images/close_help.gif' align='right' border='0'></a></td></table></div>";
		myTable = document.createElement("table");
		//myTable.style.background = "#fff repeat-x";
		//myTable.style.border="#000 1px solid";
		myTable.width = "100%";
		myTable.cellPadding = "1";

		myTBody = document.createElement("tbody");

		//Header
		myTH = document.createElement("tr");
		myTDH0 = document.createElement("td");
		ntId='noteId'+dynamicNumber;
		theNote=document.createElement("textarea");
		theNote.id='theNoteBox';
		theNote.rows='6';
		theNote.style.width='98%';

		/*myTDH0.innerHTML='<div id="'+ntId+'" style="border:solid 1px #71706e; height:100px; overflow-y:auto;overflow-x:hidden; background:#fff" contentEditable=true></div><div style="padding-top:5px;"><input type="button" value=" Save " onclick="'+el.title=ntId.innerHTML +'" > <input type="button" value=" Cancel "></div>';*/
		//document.getElementById(ntId).innerHTML=el.title;
		dynamicNumber++;
		buttonSpace=document.createElement("div");
		buttonSpace.style.padding='5px 0 0 0px;'
		buttonSpace.innerHTML="<input type='button' value=' Save ' onclick='saveNote()'> <input type='button' value=' Cancel ' onclick='hideNote()'>"
		myTDH0.appendChild(theNote);
		myTDH0.appendChild(buttonSpace);
		myTH.appendChild(myTDH0);
		myTBody.appendChild(myTH);
		myTable.appendChild(myTBody);
		d.appendChild(headerDiv);
		d.appendChild(myTable);
		document.body.appendChild(d);
		if(el.title){
		document.getElementById('theNoteBox').innerHTML=el.title

		}
		document.getElementById('theNoteBox').focus();
	}


}
function saveNote(){
	theSrcElement.title=document.getElementById('theNoteBox').innerHTML;
	var eleHTML=theSrcElement.outerHTML;
	theSrcElement.outerHTML="<span style='position:relative;' >"+eleHTML+"<img src='../../images/note_indi.gif' style='position:absolute;top:3px;right:1px;'  alt='Press F9 to view note' /></span>"


	hideNote();
}

function showHelp()
{
	el = event.srcElement;

	if(el.tagName=="INPUT" || el.tagName=="TEXTAREA" || el.tagName=="SELECT")
	{

		posLayer = findPos(el);
		d = document.createElement("div");
		d.id = "myHelpBox";
		helpBox = d;
		d.style.position="absolute";
		d.style.zIndex = "1000";
		d.style.border = "#71706e 1px solid";
		d.style.background = "#fcfbf3";
		d.style.padding ="0 5px 0 5px";
		d.style.width = "300px";
		d.style.left = posLayer[0]+2;
		d.style.top = posLayer[1]+21;

		headerDiv = document.createElement("div");
		headerDiv.innerHTML = "<div style='font-size:14px;padding:0px;color:#316597; font-weight:bold;padding-top:5px;'><table width='100%'><tr><Td><img src='../../images/help.gif'> Field Level Help </td><td align='right'> &nbsp; <a href='javascript:void(0);' onclick='hideHelp()'><img src='../../images/close_help.gif' align='right' border='0'></a></td></table></div>";
		myTable = document.createElement("table");
		//myTable.style.background = "#fff repeat-x";
		//myTable.style.border="#000 1px solid";
		myTable.width = "100%";
		myTable.cellPadding = "1";

		myTBody = document.createElement("tbody");

		//Header
		myTH = document.createElement("tr");
		myTDH0 = document.createElement("td");

		myTDH0.innerHTML='<div style="margin:5px;"><p>Help content should come here. Whatever you want to type you can put here</p><p>Help content should come here. Whatever you want to type you can put here</p><p>Help content should come here. Whatever you want to type you can put here</p> </div>';
		//document.getElementById(ntId).innerHTML=el.title;
		dynamicNumber++;
		myTH.appendChild(myTDH0);
		myTBody.appendChild(myTH);
		myTable.appendChild(myTBody);
		d.appendChild(headerDiv);
		d.appendChild(myTable);
		document.body.appendChild(d);

	}


}











var auditBox = "";

function showAudit(e)
{
	el = e?e.target:event.srcElement;
	if(el.tagName=="INPUT" || el.tagName=="TEXTAREA" || el.tagName=="SELECT")
	{
		posLayer = findPos(el);
		var offset = $(el).offset();
		d = document.createElement("div");
		d.id = "myAuditBox";
		auditBox = d;
		/*d.style.position="absolute";
		d.style.zIndex = "1000";
		d.style.border = "#18aae6 6px solid";
		d.style.background = "#fff";
		d.style.padding ="0px";
		d.style.width = "420px";*/
		d.style.left = offset.left+'px';//posLayer[0]+2;
		d.style.top = (offset.top+$(el).height()+7)+'px';//posLayer[1]+21;

		headerDiv = document.createElement("div");
		headerDiv.innerHTML = "<div style='font-size:14px;padding:4px;background:#18aae6;font-weight:bold;color:#ffffff'><table width='100%'><tr><Td><img src='images/audit_icon.gif' style='float:left;margin-right:5px' align='bottom'> Audit Trail <a href='javascript:void(0);' onclick='alert(\'View Entire List\')' style='font-size:10px;color:#ffffff'>View All (10)</a></td><td align='right'><a href='javascript:void(0);' onclick='hideAudit()'><img src='images/audit_close.gif' align='right' border='0'></a></td></table></div>";
		myTable = document.createElement("table");
		myTable.style.background = "#fff url(images/audit_bg.gif) repeat-x";
		myTable.style.border="#fff 1px solid";
		myTable.width = "100%";
		myTable.cellPadding = "1";

		myTBody = document.createElement("tbody");

		//Header
		myTH = document.createElement("tr");
		myTDH0 = document.createElement("td");
		myTDH0.width="20";
		myTDH0.innerHTML = "<b>#</b>";
		myTDH1 = document.createElement("td");
		myTDH1.innerHTML = "<b>Last Value</b>";
		myTDH2 = document.createElement("td");
		myTDH2.innerHTML = "<b>Modified By</b>";
		myTDH3 = document.createElement("td");
		myTDH3.innerHTML = "<b>Modified Date</b>";


		myTH.appendChild(myTDH0);
		myTH.appendChild(myTDH1);
		myTH.appendChild(myTDH2);
		myTH.appendChild(myTDH3);
		myTBody.appendChild(myTH);

		for(i=5;i>0;i--)
		{
			myTR = document.createElement("tr");

			myTD0 = document.createElement("td");
			myTD0.innerHTML =  i +"." ;

			myTD1 = document.createElement("td");
			myTD1.innerHTML = "<a href='javascript:void(0)' title='Apply this value'>Last Value " + i +"</a>";

			myTD2 = document.createElement("td");
			myTD2.innerHTML = "<a href='javascript:void(0)' title='View User Details'>John Orley</a>";

			myTD3 = document.createElement("td");
			myTD3.innerHTML = "12/2"+i+"/2008";


			myTR.appendChild(myTD0);
			myTR.appendChild(myTD1);
			myTR.appendChild(myTD2);
			myTR.appendChild(myTD3);
			myTBody.appendChild(myTR);
		}

		myTable.appendChild(myTBody);
		d.appendChild(headerDiv);
		d.appendChild(myTable);
		document.body.appendChild(d);
	}
}

function findPos(obj) {
	var curleft = curtop = 0;
	if (obj.offsetParent) {
		curleft = obj.offsetLeft
		curtop = obj.offsetTop
		while (obj = obj.offsetParent) {
			curleft += obj.offsetLeft
			curtop += obj.offsetTop
		}
	}
	return [curleft,curtop];
}

function deleteUser()
{
	createPopUp('src:delete_users.html','width:600','height:475','title:Delete Users')
}

function deleteRowData(r,tbl)
{
	if(confirm('Are you sure you want to Delete this Assignment'))
	{
		while(r.tagName!="TR")
		r = r.parentElement

		rIndex=r.rowIndex;
		document.getElementById(tbl).deleteRow(rIndex);
	}
}

function showSC(){
		var sc=document.getElementsByTagName('span');
		for(i=0; i<sc.length; i++)
			{
				if(sc[i].className=='short_cut')
					sc[i].className='short_cut1';
				else if(sc[i].className=='short_cut1')
					sc[i].className='short_cut';
			}
}
function show(x)
{
	if(x=='overlay1'||x=='overlay2')
		{
			//do nothing
		}
	else{
	document.getElementById(x).style.display='';
	}
}

function hide(x)
{
	document.getElementById(x).style.display='none';
}
function assignRole()
	{
		allStud('assign_role_table').style.display='';
		allStud('select_study_table').style.display='none';
		allStud('select_a_study').className='anchor_disabled';
		allStud('assign_a_role').className='anchor_enabled';
		allStud('study_back').style.display='';
		allStud('study_save').style.display='';
		allStud('study_role').style.display='none';


	}
	function selectStudy()
	{
		allStud('assign_role_table').style.display='none';
		allStud('select_study_table').style.display='';
		allStud('select_a_study').className='anchor_enabled';
		allStud('assign_a_role').className='anchor_disabled';
		allStud('study_back').style.display='none';
		allStud('study_save').style.display='none';
		allStud('study_role').style.display='';


	}

function x_expColl(tableId,theImg){

	$("#"+tableId).toggle();
	var theTable=document.getElementById(tableId);
	var theImage=document.getElementById(theImg);
	var ImageAlt=theImage.alt.toLowerCase();
	var imgPath=document.getElementById(theImg).src.split('/images/');

	if(imgPath[1]=='x_collapse.gif'){
		theImage.src=imgPath[0]+'/images/x_expand.gif';
		theImage.alt='expand';
	  }
	else if(imgPath[1]=='x_expand.gif'){
	 theImage.src=imgPath[0]+'/images/x_collapse.gif';
	 theImage.alt='collapse';
  	 }
}

function LftRtCollpase(tableId,theImg){

	$("#"+tableId).toggle();
	var theTable=document.getElementById(tableId);
	var theImage=document.getElementById(theImg);
	var ImageAlt=theImage.alt.toLowerCase();
	var imgPath=document.getElementById(theImg).src.split('/images/');

	if(imgPath[1]=='ar_left.gif'){
		theImage.src=imgPath[0]+'/images/ar_right.gif';
		theImage.alt='expand';
	  }
	else if(imgPath[1]=='ar_right.gif'){
	 theImage.src=imgPath[0]+'/images/ar_right.gif';
	 theImage.alt='collapse';
  	 }
}

function expCollapse(tableId,theImg){
	$("#"+tableId).slideToggle({
			collapsible: true
		});
	var theTable=document.getElementById(tableId);
	var theImage=document.getElementById(theImg);
	var ImageAlt=theImage.alt.toLowerCase();
	var imgPath=document.getElementById(theImg).src.split('/images/');

	if(imgPath[1]=='expanded.gif'){
		theImage.src=imgPath[0]+'/images/collapsed.gif';
		theImage.alt='expand';
	  }
	else if(imgPath[1]=='collapsed.gif'){
	 theImage.src=imgPath[0]+'/images/expanded.gif';
	 theImage.alt='collapse';
  	 }

}

function showHideSearch(tabId,tableId){
	 $("#"+tableId).slideToggle();	 var theTable=document.getElementById(tableId);
	 var theTab=document.getElementById(tabId);

if(theTab.className=='black_tab_on'){
		 theTab.className='black_tab';
  }
else if(theTab.className='black_tab'){
	 	 theTab.className='black_tab_on';
 }

}
// JavaScript Document

function expCollapsePlusMinus(tableId,theImg){

	var ids=tableId.split(',');
	var imgPath=document.getElementById(theImg).src.split('/images/');

	for(i=0;i<ids.length;i++){

	var theTable=document.getElementById(ids[i]);
	var theImage=document.getElementById(theImg);
	if(theTable.style.display==''){
		theTable.style.display='none';
		theImage.src=imgPath[0]+'/images/plus.gif';
		theImage.alt='Expand';

	  }
	else if(theTable.style.display=='none'){
	 theTable.style.display='';
 	 theImage.src=imgPath[0]+'/images/minus.gif';
	 theImage.alt='Collapse';
  	 }
	}

}

function checkIfAll(theElement,container,allCheckBox)
	{
		var counter=0;

	  	var names=allStud(container).getElementsByTagName('input');
		for(i=0;i<names.length; i++)
			{
				if(names[i].name==theElement.name)
					{
					 	if(names[i].checked==true)
					 	counter=counter;
					 		else
						counter=counter+1;

					}

			}
		if(counter==0)
			{

			allStud(allCheckBox).checked=true;

			}
		if(counter!=0)
			{

			allStud(allCheckBox).checked=false;

			}
}


function checkAllList(checkname,exby)
{
	 for (i = 0; i < checkname.length; i++)
		 checkname[i].checked = exby.checked ? true : false;
	// isAllChecked(exby.id,exby);
}

function isAllChecked(checkAll,theCheckBox)
	{
		counter=0;
		checkBoxCounter=0;
		formName = theCheckBox.form
		for (i = 0; i <formName.length; i++)
	 	{	if(formName[i].type=="checkbox")
		 		{
					checkBoxCounter = checkBoxCounter +1;
					if(formName[i].checked==true && formName[i].id!=checkAll)
						{
							counter=counter+1;
						}
				}
		}
		if(counter>=checkBoxCounter-1)
			document.getElementById(checkAll).checked=true;
		else
			document.getElementById(checkAll).checked=false;

		if(document.getElementById("selectedChecks"))
		{
			document.getElementById("selectedChecks").innerHTML = counter;
		}
}

function enableButton(theButton,theCheckBox){
		counter=0;
		checkBoxCounter=0;
		formName = theCheckBox.form
		for (i = 0; i <formName.length; i++)
	 	{	if(formName[i].type=="checkbox")
		 		{
					checkBoxCounter = checkBoxCounter +1;
					if(formName[i].checked==true)
						{
							counter=counter+1;
						}
				}
		}
	buttons=theButton.split(',');
		if(counter!=0)
		{

				for(i=0;i<buttons.length;i++)
					{
						document.getElementById(buttons[i]).disabled=false;
					}
				}

		if(counter==0){
			for(i=0;i<buttons.length;i++)
				{
				document.getElementById(buttons[i]).disabled=true;
				}
			}

		/*if(counter>1)
		document.getElementById('copy_role').disabled=true;*/

/*
		if(!document.getElementById(theButton).buttonName)
			document.getElementById(theButton).buttonName = document.getElementById(theButton).value;

		if(counter!=0)
			document.getElementById(theButton).value = document.getElementById(theButton).buttonName + " ("+counter+")";
		else
			document.getElementById(theButton).value = document.getElementById(theButton).buttonName;
			*/
}









function enableButtonOne(theButton,theCheckBox){
		counter=0;
		formName = theCheckBox.form
		for (i = 0; i <formName.length; i++)
	 	{	if(formName[i].name==theCheckBox.name)
		 		{
					if(formName[i].checked==true){ counter=counter+1}
				}
		}

		if(counter==1)
		document.getElementById(theButton).disabled=false;
		if(counter!=1)
		document.getElementById(theButton).disabled=true;
}

function MoveOption(objSourceElement, objTargetElement)
    {
        var aryTempSourceOptions = new Array();
        var x = 0;

        //looping through source element to find selected options
        for (var i = 0; i < objSourceElement.length; i++) {
            if (objSourceElement.options[i].selected) {
                //need to move this option to target element
                var intTargetLen = objTargetElement.length++;
                objTargetElement.options[intTargetLen].text = objSourceElement.options[i].text;
                objTargetElement.options[intTargetLen].value = objSourceElement.options[i].value;
            }
            else {
                //storing options that stay to recreate select element
                var objTempValues = new Object();
                objTempValues.text = objSourceElement.options[i].text;
                objTempValues.value = objSourceElement.options[i].value;
                aryTempSourceOptions[x] = objTempValues;
                x++;
            }
        }

        //resetting length of source
        objSourceElement.length = aryTempSourceOptions.length;
        //looping through temp array to recreate source select element
        for (var i = 0; i < aryTempSourceOptions.length; i++) {
            objSourceElement.options[i].text = aryTempSourceOptions[i].text;
            objSourceElement.options[i].value = aryTempSourceOptions[i].value;
            objSourceElement.options[i].selected = false;
        }
    }
		function selectAll( source_id)
			{
			var listItem= document.getElementById(source_id).getElementsByTagName('option')

			for(i=0; i<listItem.length; i++)
			{
				listItem[i].selected=true;

			}

			}


/* Start - Script for Tabs */
var prevActiveTab='tab1';
var prevActiveContent='content1';

function changeTab(theTab,theContent)
{
	document.getElementById(theTab).className='on';
	document.getElementById(theContent).style.display='';

	if(prevActiveTab!=''&&prevActiveContent!=''&&prevActiveTab!=theTab&&prevActiveContent!=theContent)
		{
		document.getElementById(prevActiveTab).className='';
		document.getElementById(prevActiveContent).style.display='none';
		prevActiveTab=theTab;
		prevActiveContent=theContent;
		}
}

var prevActiveTabLeft='LeftNavtab1';
var prevActiveContentLeft='LeftNavcontent1';

function changeTabLeft(theTab,theContent)
{

	document.getElementById(theTab).className='swap';
	document.getElementById(theContent).style.display='';

	if(prevActiveTabLeft!=''&&prevActiveContentLeft!=''&&prevActiveTabLeft!=theTab&&prevActiveContentLeft!=theContent)
		{
		document.getElementById(prevActiveTabLeft).className='';
		document.getElementById(prevActiveContentLeft).style.display='none';
		prevActiveTabLeft=theTab;
		prevActiveContentLeft=theContent;
		}
}

var prevActiveTabPreg='AsseTab';
var prevActiveContentPreg='AsseContent';

function changeAssesTab(theTab,theContent)
{

	document.getElementById(theTab).className='on';
	document.getElementById(theContent).style.display='';

	if(prevActiveTabPreg!=''&&prevActiveContentPreg!=''&&prevActiveTabPreg!=theTab&&prevActiveContentPreg!=theContent)
		{
		document.getElementById(prevActiveTabPreg).className='';
		document.getElementById(prevActiveContentPreg).style.display='none';
		prevActiveTabPreg=theTab;
		prevActiveContentPreg=theContent;
		}
}


var prevActiveTab1='pop_tab1';
var prevActiveContent1='pop_content1';

function changePopUpTab(theTab,theContent)
	{
		document.getElementById(theTab).className='on';
		document.getElementById(theContent).style.display='';

		if(prevActiveTab1!=''&&prevActiveContent1!=''&&prevActiveTab1!=theTab&&prevActiveContent1!=theContent)
			{
			document.getElementById(prevActiveTab1).className='';
			document.getElementById(prevActiveContent1).style.display='none';
			prevActiveTab1=theTab;
			prevActiveContent1=theContent;
			}
}



var prevActiveTab2='level2_tab1';
var prevActiveContent2='level2_content1';

function changeLevel2Tab(theTab,theContent)
	{
		document.getElementById(theTab).className='on';
		document.getElementById(theContent).style.display='';

	if(prevActiveTab2!=''&&prevActiveContent2!=''&&prevActiveTab2!=theTab&&prevActiveContent2!=theContent)
		{
		document.getElementById(prevActiveTab2).className='';
		document.getElementById(prevActiveContent2).style.display='none';
		prevActiveTab2=theTab;
		prevActiveContent2=theContent;
		}
}

var prevActiveTab2_1='level2_1_tab1';
var prevActiveContent2_1='level2_1_content1';

function changeLevel2_1Tab(theTab,theContent)
	{
		document.getElementById(theTab).className='on';
		document.getElementById(theContent).style.display='';

	if(prevActiveTab2!=''&&prevActiveContent2_1!=''&&prevActiveTab2_1!=theTab&&prevActiveContent2_1!=theContent)
		{
		document.getElementById(prevActiveTab2_1).className='';
		document.getElementById(prevActiveContent2_1).style.display='none';
		prevActiveTab2_1=theTab;
		prevActiveContent2_1=theContent;
		}
}

/* End - Script for Tabs */

/* Start - Codelist Content Change */

function changeCodeListFor(ele){
	allStud('codelist_for').innerHTML=ele.innerHTML;
	highLight(ele);
		}

function highLight(ele)
	{
	names= allStud('codeList_names').getElementsByTagName('a');
	for(i=0;i<names.length;i++)
		{
			names[i].className='';
		}

		ele.className= "current";
	}
function allStud(x){
return document.getElementById(x);
}

/* End - Codelist Content Change */


/*pop up script*/
var left1;
var top1;
var oldWidth;
var oldHeight
var ppdivId;
var middlePortion;
function setHeight(x,contentBox)
	{
		var height1=document.getElementById(x).offsetHeight;
		//document.getElementById(contentBox).style.height=height1-24+'px';
		$("#"+contentBox).css({height:height1-24+'px'})

	}
function showpopup(x,contentBox)
{

	ppdivId=x;
	middlePortion=contentBox;
	ele=$("#"+x);
	ele.fadeIn('fast');
	/*ele.resizable({
 		minWidth: 200,
    	minHeight: 200,
		transparent: true
	});
	ele.resize(function(){
		setHeight(x,contentBox)
	})*/


	ele.draggable({
		handle:$('.handle')
//		containment:"document"
	});

	if(ele.css('width')!='100%'){
	oldHeight=document.getElementById(x).offsetHeight;
	oldWidth=document.getElementById(x).offsetWidth;
	}

	if(ele.css('width')=='100%'){
		ele.css("height",oldHeight)
		ele.css("width",oldWidth)
	}
	middleOfScreen(x);
	if(document.getElementById('overlay'))
	showoverlay();
}
function setMaxHeight(x,y){

$("#"+y).css({height:document.documentElement.clientHeight-24+'px'})
}
function maxMin(div){

if($("#"+div).css('width')!='100%')
{

top1=$("#"+div).css('top');
left1=($("#"+div).css('left'));
$("#"+div).animate({
		height:"100%",
        width: "100%",
		top:'0px',
		left:'0px'
      }, 500 );
setMaxHeight(ppdivId,middlePortion)
}
else if($("#"+div).css('width')=='100%')
		{

		$("#"+div).animate({
		height:oldHeight,
        width: oldWidth,
		left:left1,
		top:top1

      }, 500 );
}

}
function showoverlay(){
//$("#overlay").show('fast');
document.getElementById('overlay').style.display='';
}
function closePopUp(x)
{
ele=$("#"+x)
ele.fadeOut('fast');
//$("#overlay").hide('slow');
hideOverlay();

}
/*Middle of screen*/
function middleOfScreen(ele)
{

	var theDiv = document.getElementById(ele);
	theDiv.style.left = "50%";
	theDiv.style.top = "50%";
	var temp_left = theDiv.offsetLeft;
	leftMargin = temp_left - theDiv.offsetWidth/2 ;
	if(leftMargin<0){leftMargin=0}
	theDiv.style.left=leftMargin+"PX";
	var temp_top = theDiv.offsetTop
	topMargin= temp_top - theDiv.offsetHeight/2;
	if(topMargin<0){topMargin=0}
	theDiv.style.top=topMargin+document.documentElement.scrollTop+"PX";
}

function showpopup1(x,handle)
		{
		ele=$("#"+x);
		ele.fadeIn('fast');
			ele.css({position:"absolute",zIndex:"200"})
		ele.draggable({
		handle:$(".handle")
		//containment:"document"
		});
		middleOfScreen(x);
		document.getElementById('overlay1').style.display='';
		}

function showpopup2(x,handle)
		{
		document.getElementById('overlay2').style.display='';

			//$("#"+theContent).fadeIn();

			ele=$("#"+x);
				ele.fadeIn('fast');
					ele.css({position:"absolute",zIndex:"300"})

				ele.resizable({
					minWidth: 100,
					minHeight: 100,
					transparent: true
				});
				ele.draggable({
					handle:$(".handle"),
					containment:"document"
				});


			middleOfScreen(x);
		}

function hideOverlay(){

if(document.getElementById('overlay2'))
			{
			if(document.getElementById('overlay').style.display=='' && document.getElementById('overlay1').style.display=='' && document.getElementById('overlay2').style.display=='')
			{
				document.getElementById('overlay2').style.display='none';
			}

			else if(document.getElementById('overlay').style.display=='' && document.getElementById('overlay1').style.display=='' )
				{
				document.getElementById('overlay1').style.display='none';

				}

			else if(document.getElementById('overlay').style.display=='')
			{
					document.getElementById('overlay').style.display='none';
					//document.documentElement.style.overflow='auto';
			}
			}
		 else if(document.getElementById('overlay1'))
			{
				if(document.getElementById('overlay').style.display=='' && document.getElementById('overlay1').style.display=='' )
					{
					document.getElementById('overlay1').style.display='none';
					}

				else if(document.getElementById('overlay').style.display=='')
					{
				document.getElementById('overlay').style.display='none';
				//document.body.style.overflowY = "";
					}
			}
		 else if(document.getElementById('overlay'))
			{
				if(document.getElementById('overlay').style.display=='')
					{
					document.getElementById('overlay').style.display='none';
				//document.body.style.overflowY = "";
					}
			}
	}

/*end of pop up script*/
function insRow()
{

var x=document.getElementById('code_decode_table').insertRow(-1);
var y=x.insertCell(0);
var z=x.insertCell(1);
var w=x.insertCell(2);
y.innerHTML='<select class="select" style="width:100px;"><option>Select Language</option><option>English</option><option>French</option><option>German</option><option>Russian</option></select>';
z.innerHTML='<input type="text" style="width: 100%;" class="textbox_normal" />';
w.innerHTML='<a href="javascript:void(0);" id="rowid" onclick="removeTableRow(this,\'Code List\',\'code_decode_table\')">Delete</a>';
//deleteRow(this.parentNode.parentNode.rowIndex)">Delete</a>';
}
function insertTask()
{

var x=document.getElementById('code_decode_table').insertRow(-1);
var a=x.insertCell(0);
var b=x.insertCell(1);
var c=x.insertCell(2);
var d=x.insertCell(3);
var e=x.insertCell(4);

a.innerHTML='<input type="text" value="" />';
b.innerHTML='<input type="text" style="width:50px;" value="" />&nbsp;<select><option selected="selected">Day</option><option>days</option><option>Month</option><option>Months</option><option>Year</option></select>';
c.innerHTML='<input type="text" class="text_65_rt" value="" />&nbsp;<a href="javascript: void(0);"><img src="images/calendar.gif" border="0" /></a>';
d.innerHTML='<input type="text" class="text_65_rt" value="" />&nbsp;<a href="javascript: void(0);"><img src="images/calendar.gif" border="0" /></a>';
e.innerHTML='<a onclick="window.parent.createPopUp(\'src:add_task_details.html\',\'className:pp_div1\',\'width:600\',\'height:400\',\'title:Add More Details on Task\'); window.parent.show(\'overlay1\');" value="" href="javascript:void(0);">More</a>';
}


function insRow2()
{
var x=document.getElementById('AllRisk_Table').insertRow(-1);
var a=x.insertCell(0);
var b=x.insertCell(1);
var c=x.insertCell(2);

a.innerHTML='<input type="checkbox" onclick="enableButton(\'deleteLad\',this); isAllChecked(\'allLad\',this)" name="user"/>';
b.innerHTML='<input type="text" value="" class="editable" readonly="readonly" onclick="Editable(this)" onblur="ReadOnly(this)"/>';
c.innerHTML='<input type="text" value="" class="editable" readonly="readonly" onclick="Editable(this)" onblur="ReadOnly(this)"/>';
}


/* End - Sorting*/

function showLegend()
{
	document.getElementById('legend_1').style.display = 'block';
}

function hideLegend()
{
	document.getElementById('legend_1').style.display = 'none';
}


function expCollapseLevel2(theImg,theTable)
{
	if(allStud(theImg).alt=="expand")
		{
			allStud(theTable).style.display='';
			allStud(theImg).alt="collapse";
			allStud(theImg).src="images/col_small.gif"
		}
	else if(allStud(theImg).alt=="collapse")
		{
			allStud(theTable).style.display='none';
			allStud(theImg).alt="expand";
						allStud(theImg).src="images/exp_small.gif"
		}
}


function expCollapseRole(theImg,theTable)
{
	if(allStud(theImg).alt=="expand")
		{
			allStud(theTable).style.display='';
			allStud(theImg).alt="collapse";
			allStud(theImg).src="images/collapse.gif"
		}
	else if(allStud(theImg).alt=="collapse")
		{
			allStud(theTable).style.display='none';
			allStud(theImg).alt="expand";
						allStud(theImg).src="images/expand.gif"
		}
}

function accessAll()
			{
			allStud('program_access_all').click();
			allStud('protocol_access_all').click();
			allStud('region_access_all').click();
			allStud('country_access_all').click();
			allStud('site_access_all').click();
			allStud('subject_access_all').click();
			allStud('misc_access_all').click();
			allStud('custom_access_all').click();

		}

function expand(theImg,theTable)
{
	if(allStud(theImg).alt=="expand")
		{
			allStud(theTable).style.display='';
			allStud(theImg).alt="collapse";
			allStud(theImg).src="images/collapse.gif"
		}

}


	function clickCheckBox()
		{
			 for(var i=0; i<arguments.length; i++)
				{
					$("#"+arguments[i]).click();
				}

		}

		function checkReadMain(x,y){
			if(y.checked==true)
				{
				allStud(x).checked=true;
				}
			}

function checkRead(UpdateElement,readElement)
	{
		if(allStud(readElement).checked==false && UpdateElement.checked==true )
		allStud(readElement).click();
		//allStud(readElement).disabled=true;


	}
		function checkAllSubLevel(theElement,container,allCheckBox)
	{
		var counter=0;

	  	var names=allStud(container).getElementsByTagName('input');
		for(i=0;i<names.length; i++)
			{
				if(names[i].name==theElement.name)
					{
					 	if(names[i].checked==true)
					 	counter=counter;
					 		else
						counter=counter+1;

					}

			}


		if(counter==0)
			{

			allStud(allCheckBox).checked=true;

			}
		if(counter!=0)
			{

			allStud(allCheckBox).checked=false;

			}

	 }

function checkAll(thisElement,elementNames,theContainer)
{
	var names=allStud(theContainer).getElementsByTagName('input');

	for(i=0;i<names.length; i++)
	{
		if(names[i].name==thisElement.name)
		names[i].checked=	thisElement.checked;
	}

}

/* Start -  Code for Textbox Highlighting */


function showText(e)
{
	var e=e? e : window.event;
	var el=e.target? e.target : e.srcElement;
/*
	if(el.value == el.orgValue)
		{el.value = '';}
	else if(el.value == '')
		{el.value = '';el.orgValue;}
*/
	//el.style.border = "#ffcc11 1px solid";
	//el.style.backgroundColor = "#ffffcc";
	hideAudit();
}

function hideText(e)
{
	var e=e? e : window.event;
	var el=e.target? e.target : e.srcElement;
	originalClass=el.className
/*
	if(el.value == el.orgValue)
		{el.value = '';}
	else if(el.value == '')
		{el.value = el.orgValue;}
*/
	//el.style.border = "#7eadd9 1px solid";
	//el.style.backgroundColor = "#ffffff";
	el.className=originalClass;
}

/* End -  Code for Textbox Highlighting */

function popPrompts()
{
	//	showMesage();
}

function showMesage()
{
	//$('#msg_image').src('../images/message_icon_on.gif);
}

function addAttributes()
{
	updatePage(); // Use only in proto
	if(document.forms.length>0)
	{
		for(j=0;j<document.forms.length;j++)
		{
			tempDoc = document.forms[j];
			for(i=0;i<tempDoc.length;i++)
			{
				if(tempDoc.elements[i].type=='text')
				{
					if(window.addEventListener){
						tempDoc.elements[i].addEventListener("focus", showText, false);
						tempDoc.elements[i].addEventListener("blur", hideText, false);
					}
					else
					{
						tempDoc.elements[i].attachEvent("onfocus", showText);
						tempDoc.elements[i].attachEvent("onblur", hideText);
					}
					//tempDoc.elements[i].orgValue = tempDoc.elements[i].value;
				}
			}
		}
	}
}

//Check edit used to make pages look as if they are editable instead of Add
function updatePage()
{
	loc=location.href;
	ind=loc.indexOf("?");
	and=loc.indexOf("&");
	if(and==-1)
		act = loc.substring(ind+1,loc.length);
	else
		act = loc.substring(ind+1,and);
	if(act=="edit" || act=="save")
		modifyPage(); // Add this function to every page that requires edit and pass ?edit in the URL for add pages.
}

function showIcon(theElement,theField)
 	{

	if(theField.value==''||theField.value==null)
		allStud(theElement).style.display='none';
		else allStud(theElement).style.display='';

	}


function changeHeight(e)
				{
								var pageHeight=document.documentElement.clientHeight;
				if(pageHeight>250){document.getElementById('scroll_area').style.height=pageHeight-250+'px'}
				}

var openedBubble='';
function showBubble(x)
{
		$("#"+x).slideToggle();
		if(openedBubble!=x)
		$("#"+openedBubble).slideUp();
		openedBubble=x;
}
function closeBubble(x)
{
		$("#"+x).slideUp();
}



var defaultTxt=''


function removeTxt(ele){
defaultTxt=ele.value;
if(ele.value=='Search Personnel' ||ele.value== 'Search Document' || ele.value=='Search Data'){
ele.value='';
} else{ele.select()}
}

function restoreTxt(ele)
	{
	if(!ele.value)
	{
	 ele.value=defaultTxt;
	}


 }

	function changeClass(present_class,theElement)
	{
	$('#'+theElement).toggleClass(present_class+'_on');
	}

function changeSearch()
		{
		if(document.getElementById('main_search_left').className=='main_search_left_doc')
		{
			document.getElementById('main_search_left').className='main_search_left';
			document.getElementById('main_search_field').value="Search Data";
		}
		else if(document.getElementById('main_search_left').className=='main_search_left'){
			document.getElementById('main_search_left').className='main_search_left_per';
			document.getElementById('main_search_field').value="Search Personnel";
			}
		else if(document.getElementById('main_search_left').className=='main_search_left_per'){
			document.getElementById('main_search_left').className='main_search_left_doc';
			document.getElementById('main_search_field').value="Search Document";
			}
		}


function financeRadio(url)
{
	for(i=0;i<document.create_budget_frm.budgetRadio.length;i++)
	{
		if(document.create_budget_frm.budgetRadio[i].checked == true)
			val = document.create_budget_frm.budgetRadio[i].value;

	}

	switch(val)
		{
		case "extBudget":
			allStud('ExistingBudget').style.display='';
			allStud('budgetTemplates').style.display='none';
			showpopup('ExistingBudget','middle_existingBudget');
			closePopUp("createBudget1");
			break;

		case "templateBudget":
			allStud('ExistingBudget').style.display='none';
			allStud('budgetTemplates').style.display='';
			showpopup('budgetTemplates','middle_budgetTemplates');
			/*closePopUp("createBudget1");*/
			break;
		default:
			window.location.href = url;
	}
}




function financeTemplateshow()
{
		if(document.getElementById('temp1').checked == true)
		{
			allStud('budgetNoData').style.display='none';
			allStud('budgetTemplate1').style.display='';
			allStud('CreatecostCategory1_1_table').style.display='';
			allStud('CreatecostCategory1_2_table').style.display='';
		}
		else
	{
			allStud('budgetTemplate1').style.display='';
			allStud('CreatecostCategory1_1_table').style.display='none';
			allStud('CreatecostCategory1_2_table').style.display='none';
		}
		if( document.getElementById('temp2').checked == true)
		{
			allStud('budgetNoData').style.display='none';
			allStud('budgetTemplate1').style.display='';
			allStud('CreatecostCategory1_3_table').style.display='';
			allStud('CreatecostCategory1_4_table').style.display='';
		}
		else
		{
			allStud('budgetTemplate1').style.display='';
			allStud('CreatecostCategory1_3_table').style.display='none';
			allStud('CreatecostCategory1_4_table').style.display='none';
		}
		if( document.getElementById('temp3').checked == true)
		{
			allStud('budgetNoData').style.display='none';
			allStud('budgetTemplate1').style.display='';
			allStud('CreatecostCategory1_5_table').style.display='';
			allStud('CreatecostCategory1_6_table').style.display='';
		}
		else
		{
			allStud('budgetTemplate1').style.display='';
			allStud('CreatecostCategory1_5_table').style.display='none';
			allStud('CreatecostCategory1_6_table').style.display='none';
		}
		if( document.getElementById('temp1').checked == false && document.getElementById('temp2').checked == false && document.getElementById('temp3').checked == false )
		{
			allStud('budgetNoData').style.display='';
			allStud('budgetTemplate1').style.display='none';

		}
	}


 function addToList(x,y){

		 document.getElementById(y).value='';
		 count=1;
		 for(i=1;i<x.length;i++)
		 	{
				 	if(x[i].checked==true)
		 				{
						if(count==1)document.getElementById(y).value=x[i].value;
						if(count!=1)document.getElementById(y).value=document.getElementById(y).value+','+x[i].value;
						count++;
		 				}


			}


		 }
		 function showRecPat(x)
		 {
		 document.getElementById('daily_pattern').style.display="none"
		 document.getElementById('weekly_pattern').style.display="none"
		 document.getElementById('monthly_pattern').style.display="none"
		 document.getElementById('yearly_pattern').style.display="none"
		 document.getElementById(x).style.display='';

		 }
		 function showRecPat_main(x)
		 {
		 document.getElementById('m_daily_pattern').style.display="none"
		 document.getElementById('m_weekly_pattern').style.display="none"
		 document.getElementById('m_monthly_pattern').style.display="none"
		 document.getElementById(x).style.display='';

		 }


function showHideLayer(x)
{
$('#'+x).toggle('fast')
}

/*show hide left navigation starts here*/

var leftNavShown='yes'
function showHideLeftNavigation(x,y){

		$('#'+x).css({position:"static"})
		if(leftNavShown=='yes')
			{
				document.getElementById(y).innerHTML='Show'
				leftNavShown='no';
				$('#'+x).hide('fast')
			}
		else if(leftNavShown=='no')
			{
				document.getElementById(y).innerHTML='Hide';
				leftNavShown='yes';
				$('#'+x).show('fast')
			}

}

function floatLeftNav(x,y)
	{
		if(leftNavShown=='no')
			{
				$('#'+x).css({position:"absolute"})
				$('#'+x).css({zIndex:"50"})

				/*$('#'+x).css({border:"solid 1px red"})*/
				//$('#'+x).show('fast')
				document.getElementById(x).style.display='';
				//document.getElementById(x).style.position='absolute';
				document.getElementById(y).innerHTML='Show';
				}
	}
function hideLeftNav(x,y)
	{
			if(leftNavShown=='no')
				{
					//$('#'+x).hide('slow')
					document.getElementById(x).style.display='none';
					document.getElementById(y).innerHTML='Show';

				}
	}

/*show hide left navigation ends here*/

function makeTabs(tabBox)
	  {
		 var reusableTabContents= new Array;
		 var theTabs=document.getElementById(tabBox).getElementsByTagName('a');
		for(i=0;i<theTabs.length;i++)
		  {
			reusableTabContents[i]=theTabs[i].href.split('#')[1]
			theTabs[i].href='javascript:void(0)';
		  }
		  for(i=0;i<theTabs.length;i++)
		  {
			theTabs[i].setAttribute("onclick","changeTabReusable('"+reusableTabContents+"','"+theTabs[i].id+"','"+tabBox+"','"+reusableTabContents[i]+"')")
		  }
		var x=document.getElementById(tabBox).innerHTML;
		document.getElementById(tabBox).innerHTML=x;

	  }
  function changeTabReusable(theContent,theTab,theTabBox,showContent)
		   {
			   var contents=theContent.split(',')

		  		var noTabs=document.getElementById(theTabBox).getElementsByTagName('a');
				for(i=0;i<noTabs.length;i++)
					{

						document.getElementById(contents[i]).style.display='none';
					noTabs[i].className='';
					}
		  	document.getElementById(showContent).style.display=''
			document.getElementById(theTab).className='on';
		   }

		var spanNo=0;
         function createFormElement(parentTag,eleType,ele_class){


					   ele=document.createElement('input');
					   theSpan=document.createElement('span')
					   theSpan.id='spanElement'+spanNo
					   ele.type=eleType;
					   ele.className=ele_class;
					   br=document.createElement('br')
					   a1=document.createElement('a');
					   a1.style.paddingLeft='3px'
					   a1.href="javascript:removeRow('spanElement"+spanNo+"')";

					   	 a1.innerHTML="<img src='images/plan_close.gif' style='margin-top:3px' alt='Delete'/>"

					  	 theSpan.appendChild(br);
					  	 theSpan.appendChild(ele);
					  	 theSpan.appendChild(a1);
						 document.getElementById(parentTag).appendChild(theSpan);
						spanNo++;

					   }


					   function createTwoElement(parentTag,eleType,ele_class,ele1_class){


					   ele=document.createElement('input');
					   ele1=document.createElement('input');


					   theSpan=document.createElement('span')
					   theSpan.id='spanElement'+spanNo
					   ele.type=eleType;

					   ele.className=ele_class;
					   ele1.className=ele1_class;
						ele1.style.marginLeft='3px'
					   br=document.createElement('br')
					   a1=document.createElement('a');
					   a1.style.paddingLeft='0px'
					   a1.href="javascript:removeRow('spanElement"+spanNo+"')";

					   	 a1.innerHTML="<img src='images/plan_close.gif' style='margin-top:3px' alt='Delete'/>"

					  	 theSpan.appendChild(br);
					  	 theSpan.appendChild(ele);
						 theSpan.appendChild(ele1);
					  	 theSpan.appendChild(a1);
						 document.getElementById(parentTag).appendChild(theSpan);
						spanNo++;

					   }

		function removeRow(theElement)
		{
			$('#'+theElement).remove();
		}



	var tableNo=0;
	function addSpeciality(theTable){
					     newTable=document.createElement('table')
						 newTable.id='created_table'+tableNo
						 newTable.cellSpacing='0'
						 newTable.cellPadding='0'
						 newTable.border='0'
						  tbody = document.createElement("TBODY");
					     tr=document.createElement('tr')
					  	 td1=document.createElement('td')
						 td1.style.width='150px'
						 //td1.setAttribute('className','no_padding_left')
						// td1.className='no_padding_left';
					  	 td1.innerHTML='<select class="select80"><option>Select Speciality</option><option>Select Speciality</option><option>Select Speciality</option><option>Select Speciality</option></select>'
					     td2=document.createElement('td')
						 td2.style.width='150px'
					   	 td2.innerHTML='<label><input type="checkbox" /> Board Certified</label>'
					     td3=document.createElement('td')
						 td3.style.width='100px'
					     td3.innerHTML='<label><input type="checkbox" /> Board Eligible</label>'
					  	 td4=document.createElement('td');
						 td4.innerHTML='<a style="padding:5px 0 0 0;float:left" href="javascript:void(0)" onclick=removeElement("speciality","created_table'+tableNo+'")><img src="images/delete_task.gif"/></a>'

					  	 tr.appendChild(td1)
						 tr.appendChild(td2)
						 tr.appendChild(td3)
						 tr.appendChild(td4)
						 tbody.appendChild(tr)
						 newTable.appendChild(tbody)

					     document.getElementById(theTable).appendChild(newTable);
						tableNo++;
						 }

		   function removeElement(parentElement,childElement)
		   	{
				$('#'+childElement).remove();
			}

			/*create a draggable popup*/
var popUPId=0;
var popFull= false;
zInd=200;
var openedPopupId= new Array;
var openedOverlayId= new Array;
var noOpened=0;


function createPopUp()
	{
	window.document.body.scroll = 'no';
	window.document.documentElement.style.overflow="hidden";

	theTitle='';
	theWidth='700px';
	theSrc='';
	theHeight='auto';
	theId='dynamicPopUp'+popUPId;
	isScroll='yes';
	theClass='pp_div'
	thePage='study'
	hideTab = '';
	imgPath="../../";

/*creating the dynamic overlays*/
	overlayDiv=document.createElement('div');

	overlayDiv.id='ovlay'+popUPId;
	overlayDiv.className='overlay';
	overlayDiv.style.zIndex=zInd;
	overlayDiv.innerHTML='<!--[if IE 6]><iframe scrolling="no" style="filter:progid:DXImageTransform.Microsoft.Alpha(opacity=0)" height="100%" width="100%" frameborder="0"></iframe><![endif]-->'
	document.body.appendChild(overlayDiv)

/*end of creating the dynamic overlays*/


	for(i=0;i<arguments.length;i++)
	{
	var theParameter = arguments[i].split(':')
	var parName=theParameter[0]

	switch(parName)
			{
				case 'title':
			  	theTitle=theParameter[1]
			 	break;
				case 'height':
			 	theHeight= theParameter[1]
			 	break;
				case 'width':
			 	theWidth= theParameter[1]
			 	break;
				case 'src':
				theSrc= theParameter[1]
			 	break;
				case 'id':
				theId= theParameter[1]
			 	break;
				case 'scrolling':
				isScroll= theParameter[1]
			 	break;
				case 'className':
				theClass= theParameter[1]
			 	break;
				case 'page':
				thePage= theParameter[1]
			 	break;
				case 'hide':
				hideTab= theParameter[1]
			 	break;
				case 'imgPath':
				imgPath=theParameter[1]
				break;
			}
	}


	var xy = windowSize();

	if(theWidth>1000)
	{
		theWidth = xy[0] - 80;
		theHeight  = xy[1] - 80;
		popFull =false;
	}
	iframeName='iframe'+popUPId;
	theWidth = theWidth +'px';

	newDiv=document.createElement('div');
	newDiv.className=theClass;
	newDiv.style.width=theWidth;
	newDiv.style.zIndex=zInd++;
	newDiv.style.display='block';
	newDiv.id=theId;
	newDiv.innerHTML='<div style="_background:url('+imgPath+'images/boxborder.gif);background:url('+imgPath+'images/boxborder.png);padding:6px;"><table border="0" cellspacing="0" cellpadding="0" width="100%"><tr><td class="ws_top_left">&nbsp;</td><td class="ws_top_x">&nbsp;</td><td class="ws_top_right" >&nbsp;</td></tr><tr><td class="ws_left_y">&nbsp;</td><td class="ws_middle"><div class="handle clearfix" style="position:absolute;top:0px;height:40px;left:0px;width:'+theWidth+'">&nbsp;</div><div class="pop_up_title" style="padding-bottom:5px;">'+theTitle+'</div><div class="pp_buttons"><a href="javascript:openInSeperateWindow(\''+theSrc+'\',\''+theWidth+'\',\''+theHeight+'\');destroyPopUp(\''+theId+'\');"><img src="'+imgPath+'images/button-window-pop.gif" id="button-window-pop" title=\'Open in Separate Window\' onmouseover="this.src=\''+imgPath+'images/button-window-pop-on.gif\'" onmouseout="this.src=\''+imgPath+'images/button-window-pop.gif\'"/></a><a href="javascript:destroyPopUp(\''+theId+'\')"><img src="'+imgPath+'images/button-close-focus.gif" title=\'Close \' onmouseover="this.src=\''+imgPath+'images/button-close-focus-on.gif\'" onmouseout="this.src=\''+imgPath+'images/button-close-focus.gif\'"/></a> </div><iframe name="'+iframeName+'" scrolling="'+isScroll+'" allowtransparency="yes" width="100%" height="'+theHeight+'" frameborder="0" src="'+theSrc+'?theID='+theId+'&thePage='+thePage+'&hideTab='+hideTab+'"></iframe></td><td class="ws_right_y">&nbsp;</td></tr><tr><td class="ws_bottom_left">&nbsp;</td><td class="ws_bottom_x">&nbsp;</td> <td class="ws_bottom_right">&nbsp;</td></tr></table></div>';
	popUPId++;
	document.body.appendChild(newDiv)
	/*positioning  the div in the center of the screen */
	newDiv.style.left = "50%";
	newDiv.style.top = "50%";
	var temp_left = newDiv.offsetLeft;
	leftMargin = temp_left - newDiv.offsetWidth/2 ;
	if(leftMargin<0){leftMargin=0}
	newDiv.style.left=leftMargin+"PX";
	var temp_top = newDiv.offsetTop
	topMargin= temp_top - newDiv.offsetHeight/2;
	if(topMargin<0){topMargin=0}
	newDiv.style.top=topMargin+document.documentElement.scrollTop+"PX";
	/*end of script for positioning the div in the center of the screen */

	/* save the overlays  and popups opened */
	openedPopupId[noOpened]=theId;
	openedOverlayId[noOpened]=overlayDiv.id;
	noOpened++;


	//document.documentElement.style.overflow='hidden'
	$("#"+theId).draggable({
		handle:$('.handle')
		//containment:"document"
	});

	var browser = new Object;
	browser.ua = navigator.userAgent.toLowerCase();
	browser.ie = (typeof document.getElementById!="undefined" && typeof document.all!="undefined" && typeof window.opera=="undefined");
	 $(document).bind('keyup', 'Esc', function(){ destroyPopUp(theId) });
	 theIframe=document.getElementById(theId).getElementsByTagName('iframe')
	 if(browser.ie && browser.ua.indexOf("msie 6")!=-1){
	 window.frames(iframeName).location.reload();
	 }
}


/*end of creating a draggable pop up*/
	function destroyPopUp(ele)
		{
			window.document.body.scroll = 'auto';
			window.document.documentElement.style.overflow="auto";
			//		$("#"+openedPopupId[noOpened-1]).remove();
			$("#"+ele).remove();
			$("#"+openedOverlayId[noOpened-1]).remove();
			noOpened--;
		}

	function getIdAndClose()
	{
		loc = document.location.href;
		query = loc.indexOf("theID=");
		page =  loc.indexOf("&thePage=");
		theId = loc.substring(query+6,page);
		window.parent.destroyPopUp(theId);
		/*window.parent.$("#"+theId).remove();*/


	}


function findTab()
{
	loc = document.location.href;
	query = loc.indexOf("hideTab=");
	theTab = loc.substring(query+8,loc.length);
	return theTab;
}

function findPage()
	{
		loc = document.location.href;
		query = loc.indexOf("thePage=");
		hTab = loc.indexOf("hideTab=");
		thePage = loc.substring(query+8,hTab-1);
		return thePage;
	}

function gotoTaskPage(page)
{
			switch(page)
			{
				case 'programLibrary':
			  	parent.location.href='prog_create_task_from_library.html';
			 	break;
				case 'programTemplate':
			  	parent.location.href='prog_create_task_from_template.html';
			 	break;
				case 'studyLibrary':
			  	parent.location.href='study_create_task_from_library.html';
			 	break;
				case 'studyTemplate':
			  	parent.location.href='study_create_task_from_template.html';
			 	break;
				case 'countryLibrary':
			  	parent.location.href='coun_create_task_from_library.html';
			 	break;
				case 'countryTemplate':
			  	parent.location.href='coun_create_task_from_template.html';
			 	break;
				case 'siteLibrary':
			  	parent.location.href='site_create_task_from_library.html';
			 	break;
				case 'siteTemplate':
			  	parent.location.href='site_create_task_from_template.html';
			 	break;
			}
}

function showDiv(thePage)
{

	if (thePage == "program")
	{
			document.getElementById('programCreateTask').style.display = '';
	}
	if (thePage == "study")
	{
			document.getElementById('studyCreateTask').style.display = '';
	}
	if (thePage == "country")
	{
			document.getElementById('countryCreateTask').style.display = '';
	}
	if (thePage == "site")
	{
			document.getElementById('siteCreateTask').style.display = '';
			document.getElementById('siteView').style.display = '';
	}
}


function changeDDClass(theTab,theClass)
{
	presentClass=document.getElementById(theTab).className;
	if(presentClass==theClass){document.getElementById(theTab).className=theClass+'_on';}
	if(presentClass!=theClass){document.getElementById(theTab).className=theClass}
}
function showHideLayerWC(ele)
{
	if(document.getElementById(ele).style.display=='none')
	{
		document.getElementById(ele).style.display='';
	}
	else if(document.getElementById(ele).style.display=='')
	{
		document.getElementById(ele).style.display='none';
	}
}
function upload(theField,uploaded_cv)
{
document.getElementById(uploaded_cv).innerHTML=document.getElementById(uploaded_cv).innerHTML+"<a style='font-size:12px; color:#000; font-weight:bold;' href="+document.getElementById(theField).value+" >"+document.getElementById(theField).value+"</a><br />"
}

function showChart(theElement,x,y)
	{
		if(theElement.value=='bar chart')
		{
				document.getElementById(x).style.display='none';
		document.getElementById(y).style.display='';
	}
	else{
		document.getElementById(y).style.display='none';
		document.getElementById(x).style.display='';
	}


}
	function MM_jumpMenu(targ,selObj,restore){ //v3.0
	  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
	  if (restore) selObj.selectedIndex=0;
	}

	function AG_jumpMenu(viewBy,groupBy,checkLevel,levelBy)
	{
			viewBy = viewBy.options[selObj.selectedIndex].value;
			groupBy = viewBy.options[selObj.selectedIndex].value;

			checkLevel = checkLevel.checked;
			viewBy = viewBy.options[selObj.selectedIndex].value;



	}


	function MM_jumpMenuGo(objId,targ,restore){ //v9.0
	  var selObj = null;  with (document) {
	  if (getElementById) selObj = getElementById(objId);
	  if (selObj) eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
	  if (restore) selObj.selectedIndex=0; }
	}
	 function showSites(x)
	 			{
					$('#'+x).toggle();
				}

				var liston=true;
	 function toggleListMap(){
		 if(liston==true)
		 	{
		 		$('#sites_list').hide()
		 		$('#sites_map').show()
				liston=false;
				$('#listMapToggler').html('Show Investigator List')
		 	}
		 else if(liston==false)
		 	{
		 		$('#sites_list').show()
		 		$('#sites_map').hide()
				liston=true;
				$('#listMapToggler').html('Show Investigators on Map')
		 	}


		 }
		 var  bbmap = false;
		 function changeImage()
		 	{
				 if(bbmap==false)
				 {
				document.getElementById('site_map_image1').style.display=''
				document.getElementById('site_map_image').style.display='none'
				 bbmap=true;
				 }
				 else  if(bbmap==true)
				 {
				 document.getElementById('site_map_image1').style.display='none'
				 document.getElementById('site_map_image').style.display=''
				  bbmap=false;
				 }
			}

function jumpTo (link)
   {
   var new_url=link;
   if (  (new_url != "")  &&  (new_url != null)  )
      window.location=new_url;
}





/*Resizable text area code*/

function resize(t) {
a = t.value.split('\n');
b=1;
for (x=0;x < a.length; x++) {
 if (a[x].length >= t.cols) b+= Math.floor(a[x].length/t.cols);
 }
b+= a.length;
if (b > t.rows) t.rows = b;
}

/* ENDs here*/



function multipleSelect()
{

}
var sysDate;
function findSysDate(){
					var da=new Date();
				 	today= da.getDay();
					theDate= da.getDate();
					thisMonth = da.getMonth();
					theMonth= da.getMonth();
					theYear= da.getFullYear();
					switch(theMonth)
					{
						case 0:
			  			theMonth='Jan'
						break;
						case 1:
						theMonth='Feb'
						break;
						case 2:
						theMonth='Mar'
						break;
						case 3:
						theMonth='Apr'
						break;
						case 4:
						theMonth='May'
						break;
						case 5:
						theMonth='June'
						break;
						case 6:
						theMonth='July'
						break;
						case 7:
						theMonth='Aug'
						break;
						case 8:
						theMonth='Sept'
						break;
						case 9:
						theMonth='Oct'
						break;
						case 10:
						theMonth='Nov'
						break;
						case 11:
						theMonth='Dec'
						break;

					}

					sysDate= thisMonth + '/' + theDate + '/' + theYear;

}
function writeDate(){
				var da=new Date();
				 	today= da.getDay();
					theDate= da.getDate();
					theMonth= da.getMonth();
					theYear= da.getFullYear();
					switch(today)
					{
						case 0:
			  			today='Sunday'
						break;
						case 1:
						today='Monday'
						break;
						case 2:
						today='Tuesday'
						break;
						case 3:
						today=' Wednesday'
						break;
						case 4:
						today='Thursday'
						break;
						case 5:
						today='Friday'
						break;
						case 6:
						today='Saturday'
						break;

					}
					switch(theMonth)
					{
						case 0:
			  			theMonth='Jan'
						break;
						case 1:
						theMonth='Feb'
						break;
						case 2:
						theMonth='Mar'
						break;
						case 3:
						theMonth='Apr'
						break;
						case 4:
						theMonth='May'
						break;
						case 5:
						theMonth='June'
						break;
						case 6:
						theMonth='July'
						break;
						case 7:
						theMonth='Aug'
						break;
						case 8:
						theMonth='Sept'
						break;
						case 9:
						theMonth='Oct'
						break;
						case 10:
						theMonth='Nov'
						break;
						case 11:
						theMonth='Dec'
						break;

					}
					document.write(today +', '+ theDate + ' ' + theMonth + '\ ' + theYear)

	}


// this codes are for the visit planner
function changeOccPattern(x)
	{
		if(x=='frequency based'){
		document.getElementById('f_pattern').style.display='';
		document.getElementById('e_pattern').style.display='none';
		document.getElementById('ms_pattern').style.display='none';

		}
		else if(x=='event based'){
		document.getElementById('f_pattern').style.display='none';
		document.getElementById('e_pattern').style.display='';
		document.getElementById('ms_pattern').style.display='none';

		}
		else if(x=='milestone based'){
		document.getElementById('f_pattern').style.display='none';
		document.getElementById('e_pattern').style.display='none';
		document.getElementById('ms_pattern').style.display='';

		}
		else{
		document.getElementById('f_pattern').style.display='none';
		document.getElementById('e_pattern').style.display='none';
		document.getElementById('ms_pattern').style.display='none';

		}
	}


var dyEventsCount=0;
function addMonPlanEvent(){
	theId='dyEvents'+dyEventsCount
	document.getElementById('e_pattern_content').innerHTML=document.getElementById('e_pattern_content').innerHTML+"<table width=\"100%\" id=\""+theId+"\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"  style=\"border:solid 1px #ccc; margin-bottom:10px\"class=\"form_table_inner\"  ><tr><td>Occurs <input type=\"text\" class=\"textbox_normal\" style=\"width:50px\" /> <select class=\"select\" style=\"width:80px\"><option>Days</option><option>Weeks</option><option>Months</option></select> after <input type=\"text\" class=\"textbox_normal\" style=\"width:50px\" /> subjects are <select class=\"select\" style=\"width:100px\"><option>Status 1</option><option>Status 2</option><option>Status 3</option></select></td></tr><tr><td>Occurs <input type=\"text\" class=\"textbox_normal\" style=\"width:50px\" /> <select class=\"select\" style=\"width:80px\"><option>Days</option><option>Weeks</option><option>Months</option></select> after <input type=\"text\" class=\"textbox_normal\" style=\"width:50px\" /> number of CRF(s) are <select class=\"select\" style=\"width:100px\"><option>Status 1</option><option>Status 2</option><option>Status 3</option></select></td></tr><tr><td>Occurs <input type=\"text\" class=\"textbox_normal\" style=\"width:50px\" /> <select name=\"select\" class=\"select\" style=\"width:80px\"><option>Days</option><option>Weeks</option><option>Months</option></select> after <input type=\"text\" class=\"textbox_normal\" style=\"width:50px\" /> number of subjects complete <select class=\"select\" style=\"width:100px\"><option>Cycle 1</option><option>Cycle 2</option><option>Cycle 3</option></select></td></tr><tr><td ><input type=\"checkbox\" id=\'sae_occ\'/><label for=\'sae_occ\'> On occurence of SAE schedule visit.</label>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr><tr><td style=\"padding-bottom:10px !important;padding-top:10px !important\"><a href=\"javascript:void(0)\" onclick=\"$(\'#"+theId+"\').remove()\"><img src=\"'+imgPath+'images/cancel.gif\" style=\"margin-top:0px\"  />Delete This Pattern</a></td></tr></table>";
	dyEventsCount++

}


function positionNearMouse(theDiv){
	$(".mv_event_link").click(function(e){
		$("#"+theDiv).css({display:'block',top:e.pageY+8,left:e.pageX-120})
	});

}

function windowSize() {
	var myWidth = 0, myHeight = 0;
	if( typeof( window.innerWidth ) == 'number' ) {
		myWidth = window.innerWidth; myHeight = window.innerHeight;
	} else if( document.documentElement && ( document.documentElement.clientWidth ||document.documentElement.clientHeight ) ) {
		myWidth = document.documentElement.clientWidth; myHeight = document.documentElement.clientHeight;
	} else if( document.body && ( document.body.clientWidth || document.body.clientHeight ) ) {
		myWidth = document.body.clientWidth; myHeight = document.body.clientHeight;
	}
	//window.alert( 'Width = ' + myWidth + ' and height = ' + myHeight );
	return [myWidth,myHeight];
}
function getScrollXY() {
	var scrOfX = 0, scrOfY = 0;
	if( typeof( window.pageYOffset ) == 'number' ) {
		scrOfY = window.pageYOffset; scrOfX = window.pageXOffset;
	} else if( document.body && ( document.body.scrollLeft || document.body.scrollTop ) ) {
		scrOfY = document.body.scrollTop; scrOfX = document.body.scrollLeft;
	} else if( document.documentElement && ( document.documentElement.scrollLeft || document.documentElement.scrollTop ) ) {
		scrOfY = document.documentElement.scrollTop; scrOfX = document.documentElement.scrollLeft;
	}
	//window.alert( 'Horizontal scrolling = ' + scrOfX + '\nVertical scrolling = ' + scrOfY );
	return [scrOfX,scrOfY];
}

function changeStatusBox(boxId,selectElement)
{
	var val = selectElement.value;

	switch (val)
	{
	case "PlanSetup":
		document.getElementById(boxId).className = "ps_head_table";
		document.getElementById('programStatus').className = "program_status planSetTxt";
		break;

	case "ExecuteTrack":
		document.getElementById(boxId).className = "et_head_table";
		document.getElementById('programStatus').className = "program_status exeTrackTxt";
		break;
	case "AnalyzeReport":
		document.getElementById(boxId).className = "ar_head_table";
		document.getElementById('programStatus').className = "program_status analyseTxt";
		break;
	case "CloseOut":
		document.getElementById(boxId).className = "cl_head_table";
		document.getElementById('programStatus').className = "program_status closeOutTxt";
		break;
	default:
		document.getElementById(boxId).className = "ps_head_table";
		document.getElementById('programStatus').className = "program_status planSetTxt";
	}
}

function openInSeperateWindow(theSrc,theWidth,theHeight)
{
	LeftPosition=(screen.width)?(screen.width-theWidth)/2:100;
	TopPosition=(screen.height)?(screen.height-theHeight)/2:100;

	window.open(theSrc,'','width='+theWidth+',height='+theHeight+',top='+TopPosition+',left='+LeftPosition+',scrollbars=1,resizable=1,location=0,toolbar=0,status=1');
}

function toggleLeftDiv(theId,theImg)
{
	if(document.getElementById(theId).style.display=='none')
	{
		document.getElementById(theId).style.display='';
		document.getElementById(theImg).src='images/pl_close.gif';
	}
	else
	{
		document.getElementById(theId).style.display='none';
		document.getElementById(theImg).src='images/pl_open.gif';
	}
}
 function changeFlagImage(x){
			document.getElementById('flag_image').src=document.getElementById(x).src
	}


function communicationTemplate(elm)
{
	//hiDe following 'month_table','qtr_table','actuals','budgeted'
	hideTableArray = ['coverPage','Correspondence']

	for(i=0;i<hideTableArray.length;i++)
	{
		document.getElementById(hideTableArray[i]).style.display = 'none';
	}

	if(elm.value!="Select")
	document.getElementById(elm.value).style.display = '';
}

function investigatorPersonnel(elm)
{
	hideTableArray = ['contactCredential','otherData']

	for(i=0;i<hideTableArray.length;i++)
	/*if (hideTableArray == 'coverPage')

	{
	document.getElementById('coverPage').style.display = '';
	}*/
	{
		document.getElementById(hideTableArray[i]).style.display = 'none';
	}

	if(elm.value!="Select")
	document.getElementById(elm.value).style.display = '';
}

function showCorrespondence(theId)
{
	contentIds = ['HeaderContent','FooterContent','LetterContent','FaxContent','SubjectContent','BodyContent'];

	for(i=0;i<contentIds.length;i++)
	{
		document.getElementById(contentIds[i]).style.display = 'none';
	}
			document.getElementById(theId).style.display = '';
}

var staticHeader = false;

function removeTableRow(el,what,tbName)
{
	var table = document.getElementById(tbName);
	var	noOfRows = table.rows.length;

	if(staticHeader == true)
		rowMaxDelete = 2;
	else
		rowMaxDelete = 3;


	if(noOfRows<2)
	{
		alert("Sorry you must have atleast 1 "+what+" and cannot delete this row");
	}
	else
	{
		var con = confirm("Are you sure you want to delete this "+what+" ?")
		if (con){
				while(el.tagName!="TR")
					el=el.parentNode;
				var i=el.rowIndex;
				document.getElementById(tbName).deleteRow(i);
				rowCount--;
		}
	}
	staticHeader = false;
}

var limitNewRow = 0;
var rowCount = 1;
function addTableRow(tbName)
{
	if (limitNewRow==0)
	{
		limitNewRow =100;
	}
	if(rowCount<limitNewRow){
		var table = document.getElementById(tbName);
		noOfRows = table.rows.length;
		noOfCols = table.rows[noOfRows-1].cells.length;
		lastRow  = table.rows[noOfRows-1];
		newRow = table.insertRow(noOfRows);
		for(i=0; i <noOfCols; i++)
		{
			newCell = newRow.insertCell(i);
			newCell.innerHTML = lastRow.cells[i].innerHTML;
			newCell.className = lastRow.cells[i].className;
			childEl = newCell.childNodes[0];

			if(childEl!=undefined && (childEl.tagName=="INPUT" || childEl.tagName=="TEXTAREA"))
			{
				childEl.value="";
			}
		}
		rowCount++;
	}
	else
	{
		alert("Sorry, you can only enter a maximum of "+limitNewRow+" rows");
	}

}

function addNewRow(el)
{
	while(el.tagName!="TABLE")
		el=el.parentNode;


	noOfRows = el.rows.length;
	noOfCols = el.rows[noOfRows-2].cells.length;
	lastRow  = el.rows[noOfRows-2];
	newRow = el.insertRow(noOfRows-1);

	for(i=0; i <noOfCols; i++)
	{
		newCell = newRow.insertCell(i);
		newCell.innerHTML = lastRow.cells[i].innerHTML;
		childEl = newCell.childNodes[0];

		if(childEl!=undefined && (childEl.tagName=="INPUT" || childEl.tagName=="TEXTAREA"))
		{
			childEl.value="";
		}
	}
}

function removeTableRowNew(el,what)
{
	tbName=el
	while(tbName.tagName!="TABLE")
		tbName=tbName.parentNode;

	var	noOfRows = tbName.rows.length;

	if(staticHeader == true)
		rowMaxDelete = 3;
	else
		rowMaxDelete = 4;


	if(noOfRows<=2)
	{
		alert("Sorry you must have atleast 1 "+what+" and cannot delete this row");
	}
	else
	{
		var con = confirm("Are you sure you want to delete this "+what+" ?")
		if (con){
				while(el.tagName!="TR")
					el=el.parentNode;
				var i=el.rowIndex;
				tbName.deleteRow(i);
		}
	}
	staticHeader = false;
}

function detectIEBrowser()
{
    var agt=navigator.userAgent.toLowerCase();
    var appVer = navigator.appVersion.toLowerCase();
    var is_minor = parseFloat(appVer);
    var is_major = parseInt(is_minor);
    var iePos  = appVer.indexOf('msie');
    if (iePos !=-1) {
       is_minor = parseFloat(appVer.substring(iePos+5,appVer.indexOf(';',iePos)))
       is_major = parseInt(is_minor);
    }
	var is_ie   = (iePos!=-1);
    var is_ie5up = (is_ie && is_minor >= 5);
	return is_ie5up;
}


function downloadFile()
{
	alert("This will initiate download of file - not demoed")
}

//Turn ON OFF any ICON add an _off.gif for the same icon/ also minimum alt tag
var globalElement = "";
var globalSrc = "";
var globalTitle = "";
function toggleOnOff(el)
{
		globalElement = el;
		globalSrc = el.src;
		globalTitle = (el.title!="")?el.title:el.alt;
		if(el.src.indexOf('_off')!=-1)
			t=setTimeout("switchOn()",500);
		else
			t=setTimeout("switchOff()",500);
				el.src = myImgPath+"status_on.gif";
}

function switchOn(){
	globalSrcInd = globalSrc.indexOf("_off.gif");
	newSrc = globalSrc.substring(0,globalSrcInd);
	globalElement.src = newSrc +".gif";
	globalElement.title= globalTitle;
	//applyTRColor('#ffffff',globalElement);
}

function switchOff(){
	globalSrcInd = globalSrc.indexOf(".gif");
	newSrc = globalSrc.substring(0,globalSrcInd);
	globalElement.src = newSrc +"_off.gif";
	globalElement.title= globalTitle;
	//applyTRColor('#ffffee',globalElement);
}

function applyTRColor(col,el)
{
	while(el.tagName!="TR")
		el = el.parentElement;
	el.style.background = col;
	el.style.fontStyle = 'italic';
}

//Lock Unlock End

//Delete Record
function deleteRecord()
{
	con = confirm("Are you sure you want to delete this Record?")
	if (con){
		alert("Record Successfully deleted");
		history.back();
	}
}


function errorsuppressor(){
	alert("A script error has occured, however you may continue using the screen");
	return true
}
//window.onerror=errorsuppressor


function insertWHO()
{

var x=document.getElementById('studyWho').insertRow(-1);
var y=x.insertCell(0);
var z=x.insertCell(1);
var w=x.insertCell(2);
y.innerHTML='<input type="text" style="width: 100%;" class="textbox_normal" />';
z.innerHTML='<input type="text" style="width: 100%;" class="textbox_normal" />';
w.innerHTML='<a href="javascript:void(0);" id="rowid" onclick="removeTableRow(this,\'Code List\',\'code_decode_table\')">Delete</a>';
}
/* show div onclick of Checkbox - add_documents.html*/ /* - Saravanan*/
function showContentonCheck(checkBoxName,dataId,divId)
{
	if(checkBoxName.checked == true)
	{
	document.getElementById(divId).style.display = '';
	document.getElementById(dataId).style.display = '';
	}
	else if(checkBoxName.checked == false)
	{
		document.getElementById(divId).style.display = "none";
		document.getElementById(dataId).style.display = "none";
	}
}
function hideCount(count_box)
    {
	document.getElementById(count_box).style.display='none';
	}


/* show div onclick of Checkbox - documents.html*/ /* - Saravanan*/

function showCheckBoxContent(checkBoxName,divId)
{
	if(checkBoxName.checked == true)
	{
	document.getElementById(divId).style.display = '';
	}
	else if(checkBoxName.checked == false)
	{
		document.getElementById(divId).style.display = "none";
	}
}


/* Insert Notification - add_documents.html*/ /* - Saravanan*/
function insertNotificationRow()
{
  var tempRow = document.getElementById('code_decode_table').insertRow(-1);
  var tempCell1=tempRow.insertCell(0);
  var tempCell2=tempRow.insertCell(1);
  var tempCell3=tempRow.insertCell(2);
  var tempCell4=tempRow.insertCell(3);
  var tempCell5=tempRow.insertCell(4);
  tempCell1.innerHTML='<input type="checkbox" onclick="enableButton(\'deleteUser\',this); isAllChecked(\'allUser\',this)" name="user"/>';
  tempCell2.innerHTML='<select class="wid175"><option>Checked Out</option><option selected="selected">Draft</option><option>Completed</option><option>Approved</option><option>Issued</option><option>Received</option><option>Filed</option><option>X day to Expiry</option><option>Expired</option><option>Signed</option> </select>';
  tempCell3.innerHTML='<div class="multipleSelection margin_top5"><label><input type="checkbox" name="items[]"/>Site Manager</label><br/><label><input type="checkbox" name="items[]"/>Investigator</label><br/><label><input type="checkbox" name="items[]"/>Study Admin</label><br/><label><input type="checkbox" name="items[]"/>Doc Specialist</label><br/><label><input type="checkbox" name="items[]"/>Site Manager</label><br/><label><input type="checkbox" name="items[]"/>Investigator</label><br/><label><input type="checkbox" name="items[]"/>Study Admin</label><br/><label><input type="checkbox" name="items[]"/>Doc Specialist</label><br/><label><input type="checkbox" name="items[]"/>Site Manager</label><br/></div>';
  tempCell4.innerHTML='<div class="multipleSelection margin_top5" style="width: 160px;"><label><input type="checkbox" name="items[]"/>Protocol Review Committee</label><br/><label><input type="checkbox" name="items[]"/>Study Management</label><br/><label><input type="checkbox" name="items[]"/>Site Management</label><br/></div>';
  tempCell5.innerHTML='<a href="javascript:void(0);" onclick="document.getElementById(\'code_decode_table\').deleteRow(this.parentNode.parentNode.rowIndex)">Delete</a>';
}

var oldTheDiv = "";
function showSummaryPopUp(theDiv,theSource){

  $(theSource).click(function(e){
			 sourcePosFromRight=document.documentElement.clientWidth-e.pageX

			if(oldTheDiv!="")

				$("#"+oldTheDiv).css({display:'none'});
			oldTheDiv = theDiv;

			if((sourcePosFromRight) < 500)
			  {
				$("#"+theDiv).css({display:'block',top:e.pageY+8,left:'auto',right:sourcePosFromRight})
			  }
			else
			  {
				$("#"+theDiv).css({display:'block',top:e.pageY+8,left:e.pageX-80,right:'auto'})
			  }

		});
			//,backgroundImage:'url(../../images/info_bubble.png)',backgroundRepeat:'no-repeat'
			//,backgroundImage:'url(../../images/info_bubble_right.png)',backgroundRepeat:'no-repeat'
}

function createTaskFrom(theElements){
	for(i=0;i<theElements.length;i++){
		if(theElements[i].checked==true)
			{
				if(theElements[i].value=='extTask'){closePopUp('createTask');showpopup('selectTaskFL')}
				if(theElements[i].value=='templateTask'){closePopUp('createTask');showpopup('selectTaskFT')}
				if(theElements[i].value=='newTask'){parent.location.href='create_blank_task.html'}

			}
	}
}

function changeToFrame(){
	loc=window.location.href;
	location.href='frames.html?page='+loc;
}


/* Start - Script for add_document_template1.html Page - Saravanan */
function expandFolder(tableId,theImg){

	var ids=tableId.split(',');
	for(i=0;i<ids.length;i++){

	var theTable=document.getElementById(ids[i]);
	var theImage=document.getElementById(theImg);
	if(theTable.style.display==''){
		theTable.style.display='none';
		theImage.src='images/folder_closed.gif';
		theImage.alt='Expand';

	  }
	else if(theTable.style.display=='none'){
	 theTable.style.display='';
 	 theImage.src='images/folder_open.gif';
	 theImage.alt='Collapse';
  	 }
	}


/* Start - Script */
}
function expandFolderPlusMinus(tableId,theImg)
	{

	var ids=tableId.split(',');
	for(i=0;i<ids.length;i++){

	var theTable=document.getElementById(ids[i]);
	var theImage=document.getElementById(theImg);
	if(theTable.style.display==''){
		theTable.style.display='none';
		theImage.src='images/folder_closed_plus.gif';
		theImage.alt='Expand';

	  }
	else if(theTable.style.display=='none'){
	 theTable.style.display='';
 	 theImage.src='images/folder_open_minus.gif';
	 theImage.alt='Collapse';
  	 }
	}
}
function selectDate(ele)
{
	document.getElementById('select_date').style.display='';


}
var tdateIDCount=0;

function docStatusOnOff(el){

		findSysDate();
		tdateID	='tdateID'+tdateIDCount
		cdateID	='cdateID'+tdateIDCount
		oriContent=el.parentNode.innerHTML;
		el.src="images/status_checked.gif"
		el.onclick="void(0)";
		el.className="";
		el.parentNode.innerHTML=el.parentNode.innerHTML+"<div class=\"docStatus_date\" ><div id=\""+tdateID+"\" ondblclick=\"show('" +cdateID+ "');hide('" +tdateID+ "')\" title=\"Double Click to change Date\">"+sysDate+"</div> <div id=\""+cdateID+"\" style=\"display:none\"><input type=\"text\" class=\"inputCalendar pickDate\" /></div></div>"
		tdateIDCount++;

//this is for the calendar that comes up
	var arr = $('.inputCalendar');
	for(i=0;i<arr.length;i++)
	{ arr[i].id = 'inputDate'+i;
	}
	$(".inputCalendar").bind("click", function() {
		inputID= event.srcElement.id;
		if($('#'+inputID).attr("activateDate")!="true")
		{
			$('.inputCalendar').DatePicker({
						format:'m/d/Y',
						date: '',
						current: '',
						starts: 1,
						calendars:1,
						mode: 'single',
						position: 'r',
						onBeforeShow: function(){
							$('#'+inputID).DatePickerSetDate($('#'+inputID).val(), true);

						},
						onChange: function(formated, dates){
							$('#'+inputID).val(formated);
							document.getElementById(tdateID).innerHTML=document.getElementById(inputID).value
						},
						onHide: function(){
							hide(cdateID);
							show(tdateID);
						}

					});
			$('#'+inputID).attr("activateDate","true");
		}
	})
//end of calendar script
}


//Preferences




function setNavPref(radId,imgId,imgPath)
{
    $("#"+imgId).attr({src:imgPath});
	setCookie(radId,imgId,imgPath);
}

function setCookie(radId,imgId,imgPath)
{
    $.cookie("navPreference_radId", radId,{path:'/'});
    $.cookie("navPreference_imgId", imgId,{path:'/'});
    $.cookie("navPreference_imgPath", imgPath,{path:'/'});
}

var loadPref = {
	navigation: function (){
		if(!window.parent){
		radId = $.cookie("navPreference_radId");
		imgId = $.cookie("navPreference_imgId");
		imgPath = $.cookie("navPreference_imgPath");
		$("#"+imgId).attr({src:imgPath});
		$("#"+radId).attr("checked", "checked");
		}
	}
}

function loadTheme()
{
	themeName = $.cookie("cookieTheme");
	if(themeName!=undefined)
		changeTheme(themeName);
}

function changeTheme(themeName)
{
	wp = window.parent;
	switch (themeName)
	{
	case "surface":
		wp.document.body.style.background="#fff url(images/bg_main.png) repeat-x";
		wp.$('.header_table_2').css('background','#515151');
		wp.$('.header_table_1').css('border-bottom','solid 6px #836fb1');
	break;
	case "blossom":
		wp.document.body.style.background="#fff url(images/bg_main.jpg) repeat-x";
		wp.$('.header_table_2').css('background','#24584c');
		wp.$('.header_table_1').css('border-bottom','solid 6px #00a289');
	break;
	case "flat":
		wp.document.body.style.background="#d9e0e4 url(images/bg_main.png) repeat-x";
		wp.$('.header_table_2').css('background','#4f5762');
		wp.$('.header_table_1').css('border-bottom','solid 6px #00a289');
	break;
	default:
		bColor = themeName;
		if(themeName==undefined || themeName==""){bColor = "#fff";}
		if(themeName=='blue'){bColor = "#6a99ad";}
		if(themeName=='orange'){bColor = "#e89535";}
		if(themeName=='red'){bColor = "#cf6d5b";}
		if(themeName=='green'){bColor = "#60948c";}

		wp.document.body.style.background=bColor;
		wp.document.body.style.backgroundImage="url(images/bg_main.png)";
		wp.document.body.style.backgroundRepeat="repeat-x";
		backGround = "url(images/"+themeName+"bg.jpg)"
		wp.$('.header_table_2').css('background-image',backGround);
		wp.$('.header_table_1').css('border-bottom','solid 6px #515151');
	}
	setThemeCookie(themeName);
}

function setThemeCookie(themeName)
{
   $.cookie("cookieTheme",themeName,{path:'/'});
}

function loadClock()
{
	clockType = $.cookie("cookieClock");
	if(clockType!=undefined)
		changeClock(clockType);
}

function setClock(clockType)
{
   $.cookie("cookieClock",clockType);
   changeClock(clockType);
}

function changeClock(clockType)
{
	wp = window.parent;
	if(wp.document.getElementById('topClock'))
	{
		wp.document.getElementById('analogClock').style.display = "none";
		wp.document.getElementById('digitalClock').style.display = "none";

		if(clockType=='analog')
		{
			wp.document.getElementById('analogClock').style.display = "";
		}

		if(clockType=='digital')
		{
			wp.document.getElementById('digitalClock').style.display = "";
		}

		if(clockType=='none')
		{
			wp.document.getElementById('digitalClock').style.display = "none";
		}
   }
}

function loadIconText()
{
	iconType = $.cookie("cookieIconText");
	if(iconType!=null)
		changeIconText(iconType);
}

function setIconText(iconType)
{
   $.cookie("cookieIconText",iconType);
	 changeIconText(iconType);
}

function changeIconText(iconType)
{
	wp = window.parent;
	if(wp.document.getElementById('footer'))
	{
		if(iconType=='iconImage')
		{
			wp.$('.footer_icon_text').css('display','none');
		}
		if(iconType=='iconLabel')
		{
			wp.$('.footer_icon_text').css('display','inline');
		}
	}
}

function imgBorder(themeName)
{
	var imgIds = ['surface','blossom','flat','green','red','blue','orange'];

	for(i=0;i<imgIds.length;i++)
	{
		document.getElementById('img_'+imgIds[i]).style.border="#fff 2px solid";
	}
	document.getElementById('img_'+themeName).style.border="#f90 4px solid";
}

function loadPreferenceChanges()
{
	//Theme
	themeName = $.cookie("cookieTheme");
	if(themeName!=null)
		{document.getElementById(themeName).checked = true;
		imgBorder(themeName);}
	//Clock Type
	clockType = $.cookie("cookieClock");
	if(clockType!=null)
		document.getElementById(clockType).checked = true;
	//IconType
	iconType = $.cookie("cookieIconText");
	if(iconType!=null)
		document.getElementById(iconType).checked = true;
	//Navigation Preference
	loadPref.navigation();
}

//Preferences

var sortElement = "";
var sortSrc = "";
var sortTitle = "";
function sortOnOff(el)
{
		sortElement = el;
		sortSrc = el.src;
		sortTitle = (el.title!="")?el.title:el.alt;
		if(el.src.indexOf('_off')!=-1)
			t=setTimeout("sortOn()");
		else
			t=setTimeout("sortOff()");
}

function sortOn(){
	sortSrcInd = sortSrc.indexOf("_off.gif");
	newSrc = sortSrc.substring(0,sortSrcInd);
	sortElement.src = newSrc +".gif";
	sortElement.title= sortTitle;
}

function sortOff(){
	sortSrcInd = sortSrc.indexOf(".gif");
	newSrc = sortSrc.substring(0,sortSrcInd);
	sortElement.src = newSrc +"_off.gif";
	sortElement.title= sortTitle;
}


/* Sihpping Bubble */

function showSupplyTrackPopUp(el){
	posLayer = findPos(el);
	ShowDiv = document.getElementById('supplyTrackPopup')
	//ShowDiv.style.position = 'absolute'
	ShowDiv.style.display = 'block';
	ShowDiv.style.left = posLayer[0]-330;
	ShowDiv.style.top = posLayer[1]-160;
	//$("supplyPopup").css({display:'block',top:e.pageY+8,right:sourcePosFromRight+100})
}

function showSupplyPopUp(el){
	posLayer = findPos(el);
	ShowDiv = document.getElementById('showSupplyPopUp')
	//ShowDiv.style.position = 'absolute'
	ShowDiv.style.display = 'block';
	ShowDiv.style.left = posLayer[0]-330;
	ShowDiv.style.top = posLayer[1]-160;
	//$("supplyPopup").css({display:'block',top:e.pageY+8,right:sourcePosFromRight+100})
}


  function chatTimer()
			{
			setTimeout("document.getElementById('connectingData').style.display = 'none';document.getElementById('reConnect').style.display = 'block';",2000);
			}
			function chatReconnect()
			{
			document.getElementById('reConnect').style.display = 'none';
			document.getElementById('connectingData').style.display ='';
				setTimeout("document.getElementById('connectingData').style.display = 'none';document.getElementById('chatSearch').style.display = '';document.getElementById('chatData').style.display = '';",2000);

			}


function changeLeftTab(theList,theElement)
{
	var theDivs= new Array;
	theItems = document.getElementById(theList).getElementsByTagName('a');
	for(i=0;i<theItems.length;i++)
		{
			theItems[i].className='left_tab'
			theDivs[i]=theItems[i].rel;
			document.getElementById(theDivs[i]).style.display='none';

		}
	document.getElementById(theElement.rel).style.display='';
	theElement.className='left_tab_on';

}

function changeTreeLeftTab(theList,theElement)
{
	var theDivs= new Array;
	theItems = document.getElementById(theList).getElementsByTagName('a');
	for(i=0;i<theItems.length;i++)
		{
			theItems[i].className=''
			theDivs[i]=theItems[i].rel;
			document.getElementById(theDivs[i]).style.display='none';

		}
	document.getElementById(theElement.rel).style.display='';
	theElement.className='treeSelect';

}

function changeSubTabs(theList,theElement)
{
	var theDivs= new Array;
	theItems = document.getElementById(theList).getElementsByTagName('a');
	for(i=0;i<theItems.length;i++)
		{
			theItems[i].className=''
			theDivs[i]=theItems[i].rel;
			document.getElementById(theDivs[i]).style.display='none';

		}
	document.getElementById(theElement.rel).style.display='';
	theElement.className='on';

}
/* entered by bala */



function Editable(which){
            which.readOnly = false;
            which.className = 'editing'
}

function ReadOnly(which){
            which.readOnly = true;
            which.className = 'editable'
}

function datePicker(){
            if(document.getElementById('inputDate2').style.display=='block') {
                        document.getElementById('inputDate2').style.display='none'
                        document.getElementById('date1').style.display='block'
            }
            else
            {
                        document.getElementById('inputDate2').style.display='block'
                        document.getElementById('inputDate2').focus();
                        document.getElementById('date1').style.display='none'
            }
}

function ShowUnitList(which,id){
 document.getElementById(which.id).style.display = 'none'
 document.getElementById(id).style.display = 'block'
}

function ShowUnitValue(which,val,id){
 document.getElementById(which.id).style.display = 'none'
 document.getElementById(id).style.display = 'block'
 document.getElementById(id).innerHTML = document.getElementById(which.id).options[val].text
}

function ShowForm(which,hide)
{
document.getElementById(which).style.display=''
document.getElementById(hide).style.display='none'
}

function AddRow()
{
            document.getElementById('AddRow').style.display='block'
}

function ShowEventForm(which){
whichdiv = document.getElementById('EventListView')


if (whichdiv.style.display != 'block'){
            document.getElementById('EventListView').style.display='block';
            document.getElementById('eventsview').style.display='none'
}
else
{
            document.getElementById('EventListView').style.display='none';
            document.getElementById('eventsview').style.display='block'
}
}

var manualsrc,manualalt,whichobject

function ChangeManualStatus(which){
which.src = '../../images/status_on.gif'
whichobject = which

if (which.alt == 'Yes'){
manualsrc = '../../images/status_checked_off.gif'
manualalt = 'No'
}
else
{
manualsrc = '../../images/status_checked.gif'
manualalt = 'Yes'
}
setTimeout("ManualOn()",1000);
}
function ManualOn(){
whichobject.src = manualsrc;
whichobject.alt = manualalt;
}

	 function showItems(){

			 for(i=0;i<arguments.length;i++){
			 document.getElementById(arguments[i]).style.display=''
			 }


			 }
		 function hideItems(){

			 for(i=0;i<arguments.length;i++){
			 document.getElementById(arguments[i]).style.display='none'
			 }


			 }


 function changeFlag(theCont,clickedEle,country,LangId)
	{
		var elements=document.getElementById(theCont).getElementsByTagName('a');
				for(i=0;i<elements.length;i++)
					{
						elements[i].className='ftab';
					}
				clickedEle.className='ftab_on';
				document.getElementById(LangId).innerHTML = clickedEle.title;
	}


function setCaseNav(onCaseNav)
{

			 $.cookie("onCaseNavId",onCaseNav,{path:'/'});
			window.parent.loadCaseNav();

			

}
function loadCaseNav(){

	var onCaseNavId_1= $.cookie("onCaseNavId")
		

if(document.getElementById('HideId'))
		{

		if(onCaseNavId_1=='radCaseTabNav'){			
			showItems('products_td','HideId','TSAPTabSubMenu');
			hideItems('products_td2','ShowId','RightListViewPanel','tabAerno');
			
		}
		else{
			showItems('products_td2','ShowId','RightListViewPanel','tabAerno');
			hideItems('products_td','HideId','TSAPTabSubMenu');
			
			
		}
	}
}

/*if(document.getElementById('HideId'))
		{
		if(onCaseNavId_1=='radCaseTabNav'){
			showItems('products_td','TSAPTabSubMenu');
			hideItems('products_td2','tabAerno');
		}
		else{
			showItems('products_td2','tabAerno');
			hideItems('products_td','TSAPTabSubMenu');
		}
	}
}*/
	function showHideInfoBar(theImage){



		if(document.getElementById('infoBar').className=='info_bar'){
		document.getElementById('infoBar').className='info_bar_on';
		document.getElementById(theImage).src=myImgPath+'info_down.gif';
		document.body.className='';
			 $.cookie("infoBar",'opened',{path:'/'});
		}
		else if(document.getElementById('infoBar').className=='info_bar_on'){
				document.getElementById('infoBar').className='info_bar';
				document.getElementById(theImage).src=myImgPath+'info_up.gif';
				document.body.className='bodyBg';
				 $.cookie("infoBar",'closed',{path:'/'});
				}
		}

	function showHideInfoBarHome(theImage){

		if(document.getElementById('infoBar').className=='info_bar'){
		document.getElementById('infoBar').className='info_bar_on';
		document.getElementById(theImage).src='images/info_down.gif';
		document.body.className='';
			 $.cookie("infoBar",'opened',{path:'/'});
		}
		else if(document.getElementById('infoBar').className=='info_bar_on'){
				document.getElementById('infoBar').className='info_bar';
				document.getElementById(theImage).src='images/info_up.gif';
				document.body.className='bodyBg';
				 $.cookie("infoBar",'closed',{path:'/'});
				}
		}




function loadInfoBar(){
	if($.cookie("infoBar")=='opened')
	{
		document.getElementById('infoBar').className='info_bar_on';
		document.getElementById('info_opener').src=myImgPath+'info_down.gif';
		document.body.className='';
	}
	else if($.cookie("infoBar")=='closed'){
				document.getElementById('infoBar').className='info_bar';
				document.getElementById('info_opener').src=myImgPath+'info_up.gif';
				document.body.className='bodyBg';
	}
}
function showInfoBar(){
document.getElementById('infoBar').className='info_bar_float_on';
}
function hideInfoBar(){
document.getElementById('infoBar').className='info_bar_float_off';
}

function labForm()
{
document.getElementById('FormView').style.display = 'block';
document.getElementById('ListView').style.display = 'none';
document.getElementById('ListViewLink').style.display = '';
document.getElementById('FormViewLink').style.display = 'none';


}

function labList()
{
document.getElementById('FormView').style.display = 'none';
document.getElementById('ListView').style.display = 'block';
document.getElementById('ListViewLink').style.display = 'none';
document.getElementById('FormViewLink').style.display = '';
}

function showData (it, box)
{
	var vis = (box.checked) ? "block" : "none";
	document.getElementById(it).style.display = vis;
}
function innerTabs(tabsCont,theTab){
	allTabs=document.getElementById(tabsCont).getElementsByTagName('a');
	for(i=0;i<allTabs.length;i++){
		allTabs[i].className='';
		document.getElementById(allTabs[i].rel).style.display='none'
	}
	document.getElementById(theTab).className='on';
	contShown=document.getElementById(theTab).rel;
	document.getElementById(contShown).style.display='';
}

/* Widget Tabs*/
function showtab(obj1,obj2,obj3,obj4){
	var tabl = document.getElementById(obj1);
	var tab2 = document.getElementById(obj2);
	var cnt1 = document.getElementById(obj3);
	var cnt2 = document.getElementById(obj4);
	tabl.className="da_tab_on";
	tab2.className="da_tab_off";
	cnt1.style.display="block";
	cnt2.style.display="none";
	}



/*for dynamically generated tabs in the footer section*/

function setCookieBt(theSource)
{
	if($.cookie("btSrc")==null)
	{
	   	$.cookie("btSrc",theSource, { expires: 7, path: '/'});
	}
	else
	{
	   	retSourceNa=$.cookie('btSrc')
		btTabSources=retSourceNa.split('^');
		btLen=btTabSources.length;
		var duplicateAvailable;;
		var theLink=new Array;
		var theCase=new Array;
		var saved=new Array;
		var activity=new Array;
		var critical=new Array;


				for(i=0;i<btLen;i++){
					btLoc=btTabSources[i];
					getLink=btLoc.indexOf("?tl=");
					getCase=btLoc.indexOf("?cn=");
					getSaved=btLoc.indexOf("?sv=");
					getAct=btLoc.indexOf("?act=");
					getCritical=btLoc.indexOf("?cri=");
					theLink[i] = btLoc.substring(getLink+4,btLoc.length-(btLoc.length-getCase));
					theCase[i] = btLoc.substring(getCase+4,btLoc.length-(btLoc.length-getSaved));
					saved[i] = btLoc.substring(getSaved+4,btLoc.length-(btLoc.length-getAct));
					activity[i] = btLoc.substring(getAct+5,btLoc.length-(btLoc.length-getCritical));


					critical[i]=btLoc.substring(getCritical+5,btLoc.length-(btLoc.length-btLoc.length));
				}
				stLoc=theSource;
				getLink1=stLoc.indexOf("?tl=");
				getCase1=stLoc.indexOf("?cn=");
				getSaved1=stLoc.indexOf("?sv=");
				getAct1=stLoc.indexOf("?act=");
				getCritical1=stLoc.indexOf("?cri=");
				theLink1 = stLoc.substring(getLink1+4,stLoc.length-(stLoc.length-getCase1));
				theCase1 = stLoc.substring(getCase1+4,stLoc.length-(stLoc.length-getSaved1));
				savedBt1 = stLoc.substring(getSaved1+4,stLoc.length-(stLoc.length-getAct1));
				activity1 = stLoc.substring(getAct1+5,stLoc.length-(stLoc.length-getCritical1));
				critical1=stLoc.substring(getCritical1+5,stLoc.length-(stLoc.length-btLoc.length));

				for(i=0;i<btLen;i++){
					if(theCase[i]==theCase1&&activity[i]==activity1){duplicateAvailable=true;}
				}

				if(duplicateAvailable!=true){
					retSourceNa=$.cookie('btSrc')+'^'+theSource
					$.cookie("btSrc",retSourceNa,{ expires: 7, path: '/'});
				}
	}
}


var retSource;
function retCookieBt()
{
	retSource=$.cookie("btSrc");
}


var btNumber=0;
var newBtSource='';
var btZeroCounter= true;
var conWidth=0;

function generateTabs(){

winWidth=document.documentElement.clientWidth;

	retCookieBt();
	if(retSource!=null && retSource!='')
	{
			btTabSources=retSource.split('^')
			btLen=btTabSources.length;
			var theLink1=new Array;
			var theCase1=new Array;
			var saved1=new Array;
			var activity1=new Array;
			var critical1=new Array;

			for(i=0;i<btLen;i++)
				{
					btLoc1=btTabSources[i];
					getLink1=btLoc1.indexOf("?tl=");
					getCase1=btLoc1.indexOf("?cn=");
					getSaved1=btLoc1.indexOf("?sv=");
					getAct1=btLoc1.indexOf("?act=");
					getCritical1=btLoc1.indexOf("?cri=");

					theLink1[i] = btLoc1.substring(getLink1+4,btLoc1.length-(btLoc1.length-getCase1));
					theCase1[i] = btLoc1.substring(getCase1+4,btLoc1.length-(btLoc1.length-getSaved1));
					saved1[i] = btLoc1.substring(getSaved1+4,btLoc1.length-(btLoc1.length-getAct1));
					activity1[i] = btLoc1.substring(getAct1+5,btLoc1.length-(btLoc1.length-getCritical1));
					critical1[i]=btLoc1.substring(getCritical1+5,btLoc1.length-(btLoc1.length-btLoc1.length));
				}

					btLoc2=location.href;
					getLink2=btLoc2.indexOf("?tl=");
					getCase2=btLoc2.indexOf("?cn=");
					getSaved2=btLoc2.indexOf("?sv=");
					getAct2=btLoc2.indexOf("?act=");
					getCritical2=btLoc2.indexOf("?cri=");
					theLink2 = btLoc2.substring(getLink2+4,btLoc2.length-(btLoc2.length-getCase2));
					theCase2 = btLoc2.substring(getCase2+4,btLoc2.length-(btLoc2.length-getSaved2));
					saved2 = btLoc2.substring(getSaved2+4,btLoc2.length-(btLoc2.length-getAct2));
					activity2 = btLoc2.substring(getAct2+5,btLoc2.length-(btLoc2.length-getCritical2));
					critical2=btLoc2.substring(getCritical2+5,btLoc2.length-(btLoc2.length-btLoc2.length));

				for(i=0;i<btLen;i++)
					{
						if(theCase1[i]==theCase2&&activity1[i]==activity2)
						{
							var theOnTab=i;
						}
					}

					ulBt = document.createElement('ul');
					ulBt.className='bt_tabs';
					ulBt.id='bt_ul';
					var btTabforCase='new_activities.html';
					liBt = document.createElement('li');
					liBt.id = 'bt_li'+btNumber;

					aBt = document.createElement('a');
					aBt.id = 'bt_a'+btNumber;

					aBt.href='javascript:void(0)';
					aBt.innerHTML='<span onclick="location.href=\'../common/new_activities.html\'">Cases (5)</span> <span class="bt_close"><img src="../../images/bt_close.gif" onclick="removeTab(\''+btTabforCase+'\',\''+liBt.id+'\')" onmouseover="this.src=\'../../images/bt_close1.gif\'" onmouseout="this.src=\'../../images/bt_close.gif\'"/></span>';
					liBt.appendChild(aBt);
					ulBt.appendChild(liBt);
					btNumber++;

/*section for extra tabs*/

					stackBt = document.createElement('div');
					stackBt.className='stack_div';
					stackBt.id='stack_indi_div';
					stackBt.style.display='none';
					stackBt.innerHTML='<a href="javascript:void(0)" class="stack_a" onclick="$(\'#bt_cont2\').slideToggle()">&nbsp;</a><div id="bt_cont2" class="bt_cont2" style="display:none"><ul class="stack_ul" id="stack_ul"></div></div>'
					if(document.getElementById('bottom_tabs'))
		{
					document.getElementById('bottom_tabs').appendChild(stackBt)
		}
/*section for extra tabs*/


			for(i=0;i<btLen;i++)
				{
					btLoc=btTabSources[i];

					if(btTabSources[i]!='' )
						{
							getLink=btLoc.indexOf("?tl=");
							getCase=btLoc.indexOf("?cn=");
							getSaved=btLoc.indexOf("?sv=");
							getAct=btLoc.indexOf("?act=");
							getCritical=btLoc.indexOf("?cri=");

							theLink = btLoc.substring(getLink+4,btLoc.length-(btLoc.length-getCase));
							theCase = btLoc.substring(getCase+4,btLoc.length-(btLoc.length-getSaved));
							saved = btLoc.substring(getSaved+4,btLoc.length-(btLoc.length-getAct));
							activity = btLoc.substring(getAct+5,btLoc.length-(btLoc.length-getCritical));
							critical=btLoc.substring(getCritical+5,btLoc.length-(btLoc.length-btLoc.length));

							theNewLocation=btTabSources[i];
							if(saved=='y'){
							SavedImg='<img src="../../images/saved.gif" style="margin-top:4px"/>';}
							else{
							SavedImg='<img src="../../images/not_saved.gif" style="margin-top:4px"/>';
							}
							if(critical=='y'){
							tabClass='red';}
							else{
							tabClass='blue';
							}
							liBt = document.createElement('li');
							liBt.id = 'bt_li'+btNumber;
							aBt = document.createElement('a');
							aBt.id = 'bt_a'+btNumber;
							if(i==theOnTab){
								aBt.className="on"
								}
							aBt.href='javascript:void(0)';
							aBt.innerHTML='<span id="saved'+btNumber+'"></span><span id="caseNo'+btNumber+'" onclick="location.href=\''+theNewLocation+'\'"></span><span class="bt_act" id="bt_act'+btNumber+'"></span><span class="bt_close"><img src="../../images/bt_close.gif" onclick="removeTab(\''+btTabSources[i]+'\',\''+liBt.id+'\')" onmouseover="this.src=\'../../images/bt_close1.gif\'" onmouseout="this.src=\'../../images/bt_close.gif\'"/></span>';


							liBt.appendChild(aBt);
							if(conWidth<winWidth-300)
								{
									ulBt.appendChild(liBt);
								}
								else{
									document.getElementById('stack_indi_div').style.display='';
									document.getElementById('stack_ul').appendChild(liBt)
									}


							if(document.getElementById('bt_container')){document.getElementById('bt_container').appendChild(ulBt)

							document.getElementById('saved'+btNumber).innerHTML=SavedImg;
							document.getElementById('caseNo'+btNumber).innerHTML=theCase;
							document.getElementById('caseNo'+btNumber).className=tabClass;
							document.getElementById('bt_act'+btNumber).innerHTML=activity;
							btNumber++;
							conWidth=document.getElementById('bt_container').offsetWidth;
							}
						}

			tabsCounter=i+1;
			}
			$('#bottom_tabs').hide();
			$('#icon_placeholder13').html('<div class="balloon_links" style="border-left:solid 1px #ccc;" id="multiCasesOpenerDiv"><a href="javascript:void(0)" class="footer_icons_links" id="multiCasesOpener" title="Opened Cases" ><img src="'+myImgPath+'multipleCases.gif" style="margin:3px 3px 0 0" />('+tabsCounter+')</a></div>')


			$('#multiCasesOpener').bind('click',function(e)
					{	$('#bottom_tabs').slideToggle();
					})

		}

			addMenu();
			$('#bt_ul li').rightClick(function(event){
		  			$('#rc_div').toggle();
				   	var topPos=event.pageY-110+'px';
					var leftPos=event.pageX+'px'
					$('#rc_div').css({'position' : 'absolute', 'top' :topPos, 'left' :leftPos});

    			});
			$("body").bind("click",function(e){
					if($("#rc_div").is(':visible'))
						$("#rc_div").hide();
				})


}

function removeTab(tabSource,tabId)
	{
		retSourceforRemove=$.cookie("btSrc");
		btTabSources=retSourceforRemove.split('^');
		newBtSource='';

		for(i=0;i<btTabSources.length;i++){

			if(btTabSources[i]!=tabSource)
				{

					if(btZeroCounter==true)
						{
							newBtSource=btTabSources[i]
							btZeroCounter=false;
						}
					else
						{
							newBtSource=newBtSource+'^'+btTabSources[i];
						}
				}


		}
		btZeroCounter=true;

		$.cookie("btSrc",newBtSource,{ expires: 7, path: '/'});

		rTab=document.getElementById(tabId);
		rtabParentUl=document.getElementById(tabId).parentNode

		if(rTab.getElementsByTagName('a')[0].className=='on')
			{
			//alert('classOn')
			}

		rtabParentUl.removeChild(rTab)



	}

var gel = "";
var glink = "";
function doPageAction(txt,el,link)
{
	$('#progressText').text(txt);
	gel=el;
	glink=link;
	el.disabled="true";
	callProgress();
}

//Performing a Dummy Progress...
var myPercentage=0;
function callProgress()
{
	myPercentage +=10;
	if(myPercentage<100)
	{
		tOut = window.setTimeout("callProgress()",300);
		showProgress();
	}
	else
	{
		myPercentage=1;
		window.clearTimeout(tOut);
		closeProgress();
	}
}

function showProgress()
{
	$('#overlayBox').css('display','block');
	$('#progressBox').css('display','block');
	$(".progressBar").progressBar(myPercentage);
}

function closeProgress()
{
	gel.disabled=false;
	$(".progressBar").progressBar(0);
	$('#overlayBox').css('display','none');
	$('#progressBox').css('display','none');
	parent.location.href=glink;
}

/*end of dynamically generated tabs in the footer section*/



/*dynamically find out the image path*/
var myImgPath='';
function imagePath(){
	imgPath='';
	/*loc=location.href;

	tmainFold=loc.indexOf("newPrototype/");
	tmainFold1=loc.indexOf("tsap/");

	if(tmainFold>-1){
		mainFold=loc.indexOf("newPrototype/");
		skipper=13
	}
	else if(tmainFold1>-1){
		mainFold=loc.indexOf("tsap/");
		skipper=5
	}

	if(loc.indexOf("?tl")!=-1)
		{endLoc=loc.indexOf("?tl")
		}
	else(endLoc=loc.length)
	innerFolders=loc.substring(mainFold+skipper,endLoc);

	noOfFold=innerFolders.split('/').length;
	for(i=0;i<noOfFold-1;i++){
	imgPath=imgPath+'../'
	}*/
myImgPath=imgPath+'images/';

}
imagePath();

/*End*/


//Edit the counter/limiter value as your wish

function txtlimiter(theEle,showCountArea,noOfChar){

	maxNo=Number(noOfChar)
charCount=maxNo;
txt=document.getElementById(theEle).value;	
var len = txt.length;
if(len > charCount){
        txt = txt.substring(0,charCount);
       document.getElementById(theEle).value =txt;
        return false;
}

 document.getElementById(showCountArea).innerHTML =charCount-len;
 }
//document.myform.limit.value = count-len;




		 function addMenu()
		 {

		 var rcMenuDiv=document.createElement('div');
		 rcMenuDiv.innerHTML='<div class="rc_div" id="rc_div" style="display:none"><ul class="rc_ul"><li><a href="javascript:void(0)" onclick="alert(\'this will save the case\')">Save</a></li><li><a href="javascript:void(0)" onclick="alert(\'this will save and close the case\')">Save and Complete</a></li><li><a href="javascript:void(0)" onclick="alert(\'this will pop-out the case in a new window\')">Pop-out</a></li><li><a href="javascript:void(0)" onclick="alert(\'this will delete the case\')">Delete</a></li><li><a href="javascript:void(0)" onclick="alert(\'this will close the tab\')">Close</a></li></ul></div>'
		 document.body.appendChild(rcMenuDiv)
		 }

/*Added ::: Tabs and Expanded Panels show/hide */
var tabopened = true;//textId,
function ToggleView(tabCtId,imageId){
 var TabAnchor = document.getElementById(tabCtId).getElementsByTagName('a');
 if(tabopened==true){
	 for (i=0;i<TabAnchor.length;i++)
		 {
		 //alert(TabAnchor[i].rel);
		 document.getElementById(TabAnchor[i].rel).style.display='';

		 }
		 document.getElementById(tabCtId).style.display='none';
		 //document.getElementById(textId).innerHTML='Tab(s) View';
		 document.getElementById(imageId).src='../../images/tab_content.png';
		 document.getElementById(imageId).title='Tab(s) View';
		 tabopened=false;
	 }
	 else{
	  for (i=1;i<TabAnchor.length;i++)
		 {
		 //alert(TabAnchor[i].rel);
		 document.getElementById(TabAnchor[i].rel).style.display='none';
		 }
		 document.getElementById(tabCtId).style.display='';
		 //document.getElementById(textId).innerHTML='Expand View';
		 document.getElementById(imageId).src='../../images/expand_content.png';
		 document.getElementById(imageId).title='Expand View';
		 tabopened=true;
	 }
}

var tabopened1 = false;//textId,
function ToggleView1(tabCtId,imageId){
 var TabAnchor = document.getElementById(tabCtId).getElementsByTagName('a');
 if(tabopened1==true){
	 for (i=0;i<TabAnchor.length;i++)
		 {
		 //alert(TabAnchor[i].rel);
		 document.getElementById(TabAnchor[i].rel).style.display='';

		 }
		 document.getElementById(tabCtId).style.display='none';
		 //document.getElementById(textId).innerHTML='Tab(s) View';
		 document.getElementById(imageId).src='../../images/tab_content.png';
		 document.getElementById(imageId).title='Expand View';
		 tabopened1=false;
	 }
	 else{
	  for (i=1;i<TabAnchor.length;i++)
		 {
		 //alert(TabAnchor[i].rel);
		 document.getElementById(TabAnchor[i].rel).style.display='none';
		 }
		 document.getElementById(tabCtId).style.display='';
		 //document.getElementById(textId).innerHTML='Expand View';
		 document.getElementById(imageId).src='../../images/expand_content.png';
		 document.getElementById(imageId).title='Tab(s) View';
		 tabopened1=true;
	 }
}

function cloneElement(sampleID,recipientId) {

var lastRow = $('#'+sampleID).clone();
lastRow.attr('id','');
$('#'+recipientId).append(lastRow);
}

function deleteChecked(tbl)
{
	var tbl = document.getElementById(tbl);
	if (tbl.tBodies[0].rows[i].getAttribute('type') == 'checkbox') {
		alert(tbl.tBodies[0].rows[i].getAttribute('type') == 'checkbox');
		var checkedObjArray = new Array();
		var cCount = 0;

		for (var i=0; i<tbl.tBodies[0].rows.length; i++) {
			if (tbl.tBodies[0].rows[i].getAttribute('type') == 'checkbox' || tbl.tBodies[0].rows[i].myRow.three.checked) {
				checkedObjArray[cCount] = tbl.tBodies[0].rows[i];
				cCount++;
			}
		}
		if (checkedObjArray.length > 0) {
			var rIndex = checkedObjArray[0].sectionRowIndex;
			deleteRows(checkedObjArray);
			reorderRows(tbl, rIndex);
		}
	}
}
