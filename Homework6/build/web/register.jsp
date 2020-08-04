<%-- 
    Document   : register
    Created on : Apr 4, 2017, 5:50:18 PM
    Author     : clotifoth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="rb" class="app.RegisterBean" scope="application" />
<jsp:setProperty name="rb" property="*"/>
﻿<!DOCTYPE html>
<%@ taglib prefix="t" uri= "/WEB-INF/tlds/newtag_library" %>
<html lang="en" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Sign in</title>
        <link href="./developerWorks_registration_files/www.css" rel="stylesheet" title="www" type="text/css" />
        <!-- Masthead/footer -->
        <link href="./developerWorks_registration_files/dw-mf.css" rel="stylesheet" title="www" type="text/css" />
        <link href="./developerWorks_registration_files/dw-mf-minimal-N.css" rel="stylesheet" title="www" type="text/css" />
        <!-- Home CSS -->
        <link href="./developerWorks_registration_files/dwwi-v17.css" rel="stylesheet" title="www" type="text/css" />
        <link href="./developerWorks_registration_files/assign2b.css" rel="stylesheet" title="www" type="text/css" />
        <link href="./developerWorks_registration_files/assign3.css" rel="stylesheet" title="www" type="text/css" />

        <script>
			var element;
			var req;
            function countFields()
            {
                var outputElement1 = document.getElementById("countFieldsOutput1");
				var outputElement2 = document.getElementById("countFieldsOutput2");
                var numInput = document.getElementsByTagName("input").length;
                var numPassword = 0;

                for(var i = 0; i < document.getElementsByTagName("input").length; i++)
                {
                    if(document.getElementsByTagName("input")[i].type === "password") numPassword++;
                }

                outputElement1.innerHTML =  "The number of input elements is " + numInput + ".";
                outputElement2.innerHTML =  "The number of input elements of type password is " + numPassword + ".";

            }
			function validateTextField2(ele){
				element = ele;
				var url = "http://localhost:8080/Homework4/NewServlet?name=" + ele.value;
				req = new XMLHttpRequest();
				req.open("GET", url, true);
				req.onreadystatechange = nameValidation;
				req.send(null);
			}
			function nameValidation(){
				if(req.readyState == 4 && req.status == 200){
					if(req.responseText != "Okay"){
						displayValidationError(element,req.responseText);
					}
					else{
						removeValidationError(element.parentNode);
					}
				}
			}
			function validateDropDown2(ele){
				element = ele;
				var url = "http://localhost:8080/Homework4/NewServlet?country=" + ele.selectedOptions["0"].text;
				req = new XMLHttpRequest();
				req.open("GET", url, true);
				req.onreadystatechange = dropDownValidation;
				req.send(null);
			}
			function dropDownValidation(){
				if(req.readyState == 4 && req.status == 200){
					if(req.responseText != "Okay"){
						displayValidationError(element,req.responseText);
					}
					else{
						removeValidationError(element.parentNode);
					}
				}
			}
			
            function validateTextField(ele)
            {
                if(ele.value == "")
                {
                    displayValidationError(ele, "Please enter a value.");
                }
                else
                {
                    removeValidationError(ele.parentNode);
                }
            }
            function validateDropDown(ele)
			{
                if(ele.selectedOptions["0"].text == "Select one") 
                {
                    displayValidationError(ele, "Please enter a value.");
                }
                else
                {
                    removeValidationError(ele.parentNode);
                }
            }
            function validateEmail(ele)
            {
                if(!ele.value.includes('@'))
                {
                    displayValidationError(ele, "Please enter a valid email address.");
                }
                else
                {
                    removeValidationError(ele.parentNode);
                }
            }
            function displayValidationError(ele, message)
            {
                for(var i = 0; i < document.getElementsByClassName("validation-error").length; i++)
                {
                    if(document.getElementsByClassName("validation-error")[i].classList.contains(ele.getAttribute("id")))
                    {
                        return;
                    }
                }
                var n = document.createElement("span");
                var text = document.createTextNode(message);
                n.appendChild(text);
                n.className += "validation-error ";
                n.className += ele.getAttribute("id") + " ";
                ele.parentNode.appendChild(n);
            }
            function removeValidationError(ele)
            {
                for(var i = 0; i < ele.childNodes.length; i++)
                {
					if(ele.childNodes[i].classList != null)
					{
						if(ele.childNodes[i].classList.contains("validation-error"))
						{
							ele.removeChild(ele.childNodes[i]);
							return;
						}
					}
                }
            }
        </script>
    </head>
    <body id="ibm-com">
        <div id="ibm-top" class="ibm-landing-page">
            <div id="ibm-masthead">
                <div id="dw-masthead-top-row">
                    <ul id="ibm-mast-options-dw">
                        <li id="dw-mast-top-4">
                            <a href="http://www.ibm.com/developerworks/"><img src="./developerWorks_registration_files/dwn-dw-badge.png" width="43" height="22" alt="dW" /></a>
                        </li>
                        <li id="dw-mast-top-3">
                            <a href="http://www.ibm.com/">IBM</a>
                        </li>
                    </ul>
                </div>
                <div id="ibm-universal-nav-dw">
                    <ul id="ibm-unav-links-dw">
                        <li id="ibm-unav-home-dwlogo">
                            <a href="http://www.ibm.com/developerworks/"><img src="./developerWorks_registration_files/dwn-dw-wordmark.png" width="225" height="28" alt="developerWorks®" /></a>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="fdiv" class="ibm-access"></div>
            <div id="ibm-leadspace-head" class="ibm-alternate ibm-alternate-5col">
                <div id="ibm-leadspace-body">
                    <h1 class="ibm-small">developerWorks registration</h1>
                </div>
            </div>
            <div id="ibm-content">
                <div id="ibm-content-body">
                    <div id="ibm-content-main">
                        <div class="ibm-columns">
                            <!-- Begin 6-4 6-2 grid -->
                            <div class="ibm-col-6-4">
                                <!-- Begin 6-4 grid -->
                                <h2> <t:Hello /> </h2>
                                <p>Thank you for registering with IBM developerWorks. To  simplify things, you can use the IBM ID and password that you designate below across IBM.</p>
                                <p>Asterisks (<span class="ibm-required">*</span>) indicate fields required to complete this transaction.</p>
                                <form action="/hw6/submit" class="ibm-row-form" id="registerform" method="post">
                                    <h2 class="ibm-inner-subhead">Basic registration information</h2>
                                    <div class="ibm-columns">
                                        <div class="ibm-col-2-1">
                                            <p>
                                                <label for="FName">First name:<span class="ibm-required">*</span></label>
                                                <span><input id="FName" name="first-name" size="36" type="text" value="${rb.firstName}" onblur="validateTextField(this);"/></span>
                                            </p>
                                        </div>
                                        <div class="ibm-col-2-1">
                                            <p>
                                   
                                                <label for="LName">Last name:<span class="ibm-required">*</span></label>
                                                <span><input id="LName" name="last-name" size="36" type="text" value="${rb.lastName}" onblur="validateTextField(this);"/></span>
                                            </p>
                                        </div>
                                    </div>
                                    <p>
                                        <label for="emailAddress">Email address:<span class="ibm-required">*</span><br />
                                        <span class="ibm-additional-info dw-lc-labeloverride dw-lc-important-adjust ibm-item-note">(This will also be your IBM ID for signing in)</span></label>
                                        <span><input id="emailAddress" name="email" size="42" type="text" value="${rb.email}" onblur="validateEmail(this);"/></span>
                                    </p>
                                    <p>
                                        <label for="Password">Password:<span class="ibm-required">*</span><br /> <span class="ibm-additional-info dw-lc-labeloverride dw-lc-important-adjust ibm-item-note">(Must be at least 8 characters)</span></label>
                                        <span><input id="Password" size="42" type="password" value="${rb.password}" onblur="validateTextField(this);" name="password" class="pass"/> </span>
                                    </p>
                                    <p>
                                        <label for="RePassword">Verify password:<span class="ibm-required">*</span></label>
                                        <span><input id="RePassword" size="42" type="password" value="${rb.password}" onblur="validateTextField(this);"/></span>
                                        <span class="dw-lc-formerror" id="repassword_mismatch" style="display:none;">The passwords did not match.</span>
                                        <span class="dw-lc-formerror" id="repassword_invalid" style="display:none;">The password you entered is not valid.</span>
                                        <span class="dw-lc-formconfirm" id="repassword_valid" style="display:none;">&nbsp;</span>
                                    </p>
                                    <p>
                                        <label for="alias">Display name:<span class="ibm-required">*</span><br />
                                        <span class="ibm-additional-info dw-lc-labeloverride dw-lc-important-adjust ibm-item-note">(Must be between 3 - 31 characters.</span>
                                        </label>
                                        <span><input id="alias" size="42" type="text" value="${rb.username}" name="username" onblur="validateTextField2(this);"/></span>
                                        <span class="dw-lc-formerror" id="alias_invalid" style="display:none;">This display name is not valid. Please choose another.</span>
                                        <span class="dw-lc-formconfirm" id="alias_valid" style="display:none;">&nbsp;</span>
                                    </p>
                                    <p>
                                        <label for="countryResidence">Country/region of residence:<span class="ibm-required">*</span><br />
                                        <span class="ibm-additional-info dw-lc-labeloverride dw-lc-important-adjust ibm-item-note">(Required for warranty)</span></label>
                                        <span>
                                            <select id="countryResidence" name="country" onblur="validateDropDown2(this);">
						<option ${rb.country==""?'selected="selected"' : ''} value="">Select one</option>
                                                <option ${rb.country=="US"?'selected="selected"' : ''} value="US">United States</option>
                                                <option ${rb.country=="DE"?'selected="selected"' : ''} value="DE">Germany</option>
                                                <option ${rb.country=="KR"?'selected="selected"' : ''} value="KR">Korea, Republic of</option>
                                                <option ${rb.country=="RU"?'selected="selected"' : ''} value="RU">Russian Federation</option>
                                                <option ${rb.country=="SE"?'selected="selected"' : ''} value="SE">Sweden</option>
                                            </select>
                                        </span>
                                    </p>
                                    <div class="ibm-columns">
                                        <div class="ibm-col-2-1">
                                            <p>
                                                <label for="City">City:</label>
                                                <span><input id="City" name="city" size="36" type="text" value="${rb.city}"/></span>
                                            </p>
                                        </div>
                                        <div class="ibm-col-2-1">
                                            <p>
                                                <label for="Language">Language:<span class="ibm-required">*</span></label>
                                                <span>
                                                    <select id="Language" onblur="validateDropDown(this);" name="language">
							<option ${rb.language==""?'selected="selected"' : ''} value="">Select one</option>
                                                        <option ${rb.language=="en-US"?'selected="selected"' : ''} value="en-US">English</option>
                                                        <option ${rb.language=="de-DE"?'selected="selected"' : ''} value="de-DE">German</option>
                                                        <option ${rb.language=="ko-KR"?'selected="selected"' : ''} value="ko-KR">Korean</option>
                                                        <option ${rb.language=="ru-RU"?'selected="selected"' : ''} value="ru-RU">Russian</option>
                                                        <option ${rb.language=="sv-SE"?'selected="selected"' : ''} value="sv-SE">Swedish</option>
                                                    </select>
                                                </span>
                                            </p>
                                        </div>
                                    </div>
                                    <p>Please select a security question that only you can answer or create your own. Then enter the answer to the question. Occasionally, you may be asked to answer this question to confirm your identity.</p>
                                    <div class="ibm-columns">
                                        <div class="ibm-col-2-1">
                                            <p>
                                                <label for="SecurityQues">Security question:<span class="ibm-required">*</span></label>
                                                <span>
                                                    <select id="SecurityQues" onchange="removetempfunction()" onblur="validateDropDown(this);" name="security-question">
                                                        <option ${rb.securityQuestion==""?'selected="selected"' : ''}selected="selected" value="">Select one</option>
                                                        <option ${rb.securityQuestion=="name"?'selected="selected"' : ''} value="name">What is your mother's maiden name?</option>
                                                        <option ${rb.securityQuestion=="pet"?'selected="selected"' : ''}value="pet">What is the name of your first pet?</option>
                                                        <option ${rb.securityQuestion=="school"?'selected="selected"' : ''}value="school">What was the name of your first school?</option>
                                                        <option ${rb.securityQuestion=="job"?'selected="selected"' : ''}value="job">In what city or town was your first job?</option>
                                                        <option ${rb.securityQuestion=="country"?'selected="selected"' : ''}value="country">In what country were you born?</option>
                                                    </select>
                                                </span>
                                            </p>
                                        </div>
                                        <div class="ibm-col-2-1">
                                            <p id="customquestioncontainer" style="display:none;">
                                                <label for="custom_question">Place a question in this text field.</label>
                                                <span><input id="custom_question" size="42" type="text" value="${rb.securityQuestion}" onblur="validateTextField(this);"/></span>
                                            </p>
                                        </div>
                                    </div>
                                    <p>
                                        <label for="SecurityAns">Answer to security question:<span class="ibm-required">*</span></label>
                                        <span><input id="SecurityAns" name="security-answer" size="42" type="text" value="${rb.securityAnswer}" onblur="validateTextField(this);"/></span>
                                    </p>
                                    <div class="dw-lc-spacevertical">&nbsp;</div>
                                    <p>
                                        <input id="count-fields" class="ibm-btn-arrow-pri" value="Count Fields" type="button" onclick="countFields();" />
                                        <input id="ibm-submit" class="ibm-btn-arrow-pri" value="Register" type="submit" />
    <p id="countFieldsOutput1">The number of input elements is </p>
 <br><p id="countFieldsOutput2">The number of input elements of type password is </p>
                                    <div class="dw-lc-spacevertical">&nbsp;</div>
                                    <div class="dw-lc-spacevertical">&nbsp;</div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
