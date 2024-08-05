package com.aerosecgeek.emailthreatlensservice.modules.testdata;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MessageExample {
    public static Message getMessage() throws MessagingException {
        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");

        // Create a mail session
        Session session = Session.getDefaultInstance(properties, null);

        // Create a MimeMessage object
        MimeMessage message = new MimeMessage(session);

        // Set the From address
        message.setFrom(new InternetAddress("sender@example.com"));

        // Set the To address
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("recipient@example.com"));

        // Set the Subject
        message.setSubject("Test Email");

        // Add custom headers
        message.addHeader("X-Priority", "1");
        message.addHeader("X-Mailer", "JavaMail Test");

        // Set HTML content with a hyperlink
        String htmlContent = "<h1>Hello!</h1>"
                + "<p>This is a test email with a <a href='https://www.example.com'>hyperlink</a>.</p>";
        message.setContent(htmlContent, "text/html");

        // Save changes to the message
        message.saveChanges();
        return message;
    }

    public static String getHtmlContent() {
        return """
                <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                <html xmlns="http://www.w3.org/1999/xhtml">
                	<head>
                        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                        <title>Title</title>
                        <style type="text/css">
                			/* /\\/\\/\\/\\/\\/\\/\\/\\/ CLIENT-SPECIFIC STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */
                			#outlook a{padding:0;} /* Force Outlook to provide a "view in browser" message */
                			.ReadMsgBody{width:100%;} .ExternalClass{width:100%;} /* Force Hotmail to display emails at full width */
                			.ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height: 100%;} /* Force Hotmail to display normal line spacing */
                			body, table, td, p, a, li, blockquote{-webkit-text-size-adjust:100%; -ms-text-size-adjust:100%;} /* Prevent WebKit and Windows mobile changing default text sizes */
                			table, td{mso-table-lspace:0pt; mso-table-rspace:0pt;} /* Remove spacing between tables in Outlook 2007 and up */
                			img{-ms-interpolation-mode:bicubic;} /* Allow smoother rendering of resized image in Internet Explorer */
                                
                			/* /\\/\\/\\/\\/\\/\\/\\/\\/ RESET STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */
                			body{margin:0; padding:0;}
                			img{border:0; height:auto; line-height:100%; outline:none; text-decoration:none;}
                			table{border-collapse:collapse !important;}
                			body, #bodyTable, #bodyCell{height:100% !important; margin:0; padding:0; width:100% !important;}
                                
                			/* /\\/\\/\\/\\/\\/\\/\\/\\/ TEMPLATE STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */
                                
                			/* ========== Page Styles ========== */
                                
                			#bodyCell{padding:20px;}
                			#templateContainer{width:600px;}
                                
                			body, #bodyTable{
                				/*@editable*/ background-color:#EEEEEE;
                			}
                                
                			#bodyCell{
                				/*@editable*/ border-top: none;
                			}
                                
                			#templateContainer{
                                border-radius: 40px;
                                overflow: hidden;
                			}
                                
                			ul.bodyList {
                				padding-left: 0;
                				list-style: none;
                			}
                                
                            div.bodyList {
                                margin-top: 1em;
                                margin-bottom: 1em;
                            }
                                
                			h1{
                				/*@editable*/ color:#548970 !important;
                				display:block;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:26px;
                				/*@editable*/ font-style:normal;
                				/*@editable*/ font-weight:bold;
                				/*@editable*/ line-height:100%;
                				/*@editable*/ letter-spacing:normal;
                				margin-top:0;
                				margin-right:0;
                				margin-bottom:20px;
                				margin-left:0;
                				/*@editable*/ text-align:left;
                			}
                                
                			h2{
                				/*@editable*/ color:#404040 !important;
                				display:block;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:20px;
                				/*@editable*/ font-style:normal;
                				/*@editable*/ font-weight:bold;
                				/*@editable*/ line-height:100%;
                				/*@editable*/ letter-spacing:normal;
                				margin-top:0;
                				margin-right:0;
                				margin-bottom:10px;
                				margin-left:0;
                				/*@editable*/ text-align:left;
                			}
                                
                			h3{
                				/*@editable*/ color:#606060 !important;
                				display:block;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:16px;
                				/*@editable*/ font-style:italic;
                				/*@editable*/ font-weight:normal;
                				/*@editable*/ line-height:100%;
                				/*@editable*/ letter-spacing:normal;
                				margin-top:0;
                				margin-right:0;
                				margin-bottom:10px;
                				margin-left:0;
                				/*@editable*/ text-align:left;
                			}
                                
                			h4{
                				/*@editable*/ color:#808080 !important;
                				display:block;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:14px;
                				/*@editable*/ font-style:italic;
                				/*@editable*/ font-weight:normal;
                				/*@editable*/ line-height:100%;
                				/*@editable*/ letter-spacing:normal;
                				margin-top:0;
                				margin-right:0;
                				margin-bottom:10px;
                				margin-left:0;
                				/*@editable*/ text-align:left;
                			}
                                
                            .cta-button{
                                background-color: #548970 !important;display: inline-block !important;padding: 10px 15px !important;color: white !important;border-radius: 6px !important;text-decoration: none !important;
                            }
                                
                			#templatePreheader{
                				/*@editable*/ background-color:#F4F4F4;
                				/*@editable*/ border-bottom:1px solid #CCCCCC;
                			}
                                
                			.preheaderContent{
                				/*@editable*/ color:#808080;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:10px;
                				/*@editable*/ line-height:125%;
                				/*@editable*/ text-align:left;
                			}
                                
                			.preheaderContent a:link, .preheaderContent a:visited, /* Yahoo! Mail Override */ .preheaderContent a .yshortcuts /* Yahoo! Mail Override */{
                				/*@editable*/ color:#606060;
                				/*@editable*/ font-weight:normal;
                				/*@editable*/ text-decoration:underline;
                			}
                                
                			#templateHeader{
                				/*@editable*/ background-color:#F4F4F4;
                                
                				/*@editable*/ border-bottom: none;
                			}
                                
                			.headerContent{
                				/*@editable*/ color:#505050;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:20px;
                				/*@editable*/ font-weight:bold;
                				/*@editable*/ line-height:100%;
                				/*@editable*/ padding: 20px 40px;
                				/*@editable*/ text-align:left;
                				/*@editable*/ vertical-align:middle;
                				background-color: white;
                				border-bottom: 1px solid #eeeeee;
                			}
                                
                			.headerContent a:link, .headerContent a:visited, /* Yahoo! Mail Override */ .headerContent a .yshortcuts /* Yahoo! Mail Override */{
                				/*@editable*/ color:#EB4102;
                				/*@editable*/ font-weight:normal;
                				/*@editable*/ text-decoration:underline;
                			}
                                
                			.headerContent h1{
                                font-size: 28px !important;
                                background-color: #fff;
                				color: #444444 !important;
                				margin: 0;
                			}
                                
                			#headerImage{
                				height:auto;
                				max-width:600px;
                			}
                                
                			/* ========== Body Styles ========== */
                			#templateBody{
                				/*@editable*/ background-color:white;
                				/*@editable*/ border-top: none;
                				/*@editable*/ border-bottom: none;
                			}
                                
                			.bodyContent{
                				/*@editable*/ color:#505050 !important;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:14px;
                				/*@editable*/ line-height:150%;
                				padding: 20px 40px;
                				/*@editable*/ text-align:left;
                			}
                                
                			.bodyContent a:link, .bodyContent a:visited, /* Yahoo! Mail Override */ .bodyContent a .yshortcuts /* Yahoo! Mail Override */{
                				/*@editable*/ color:#EB4102;
                				/*@editable*/ font-weight:normal;
                				/*@editable*/ text-decoration:underline;
                			}
                                
                			.bodyContent img{
                				display:inline;
                				height:auto;
                				max-width:560px;
                			}
                                
                            .bodyContent p, .bodyContent ul, .bodyContent li, .bodyContent span, .bodyContent em, .bodyContent td, .bodyContent strong {
                                color:#505050 !important;
                            }
                                
                			/* ========== Footer Styles ========== */
                                
                			#templateFooter{
                				/*@editable*/ background-color:#F4F4F4;
                				/*@editable*/ border-top:none;
                			}
                                
                			.footerContent{
                				/*@editable*/ color:#808080;
                				/*@editable*/ font-family:Helvetica;
                				/*@editable*/ font-size:10px;
                				/*@editable*/ line-height:150%;
                				padding-top:20px;
                				padding-right:20px;
                				padding-bottom:20px;
                				padding-left:20px;
                				/*@editable*/ text-align:left;
                			}
                                
                			.footerContent a:link, .footerContent a:visited, /* Yahoo! Mail Override */ .footerContent a .yshortcuts, .footerContent a span /* Yahoo! Mail Override */{
                				/*@editable*/ color:#606060;
                				/*@editable*/ font-weight:normal;
                				/*@editable*/ text-decoration:underline;
                			}
                                
                			/* /\\/\\/\\/\\/\\/\\/\\/\\/ MOBILE STYLES /\\/\\/\\/\\/\\/\\/\\/\\/ */
                                
                            @media only screen and (max-width: 480px){
                				/* /\\/\\/\\/\\/\\/\\/ CLIENT-SPECIFIC MOBILE STYLES /\\/\\/\\/\\/\\/\\/ */
                				body, table, td, p, a, li, blockquote{-webkit-text-size-adjust:none !important;} /* Prevent Webkit platforms from changing default text sizes */
                                body{width:100% !important; min-width:100% !important;} /* Prevent iOS Mail from adding padding to the body */
                                
                				/* /\\/\\/\\/\\/\\/\\/ MOBILE RESET STYLES /\\/\\/\\/\\/\\/\\/ */
                				#bodyCell{padding:10px !important;}
                                
                				/* /\\/\\/\\/\\/\\/\\/ MOBILE TEMPLATE STYLES /\\/\\/\\/\\/\\/\\/ */
                                
                				/* ======== Page Styles ======== */
                				#templateContainer{
                					max-width:600px !important;
                					/*@editable*/ width:100% !important;
                				}
                                
                				h1{
                					/*@editable*/ font-size:24px !important;
                					/*@editable*/ line-height:100% !important;
                				}
                                
                				h2{
                					/*@editable*/ font-size:20px !important;
                					/*@editable*/ line-height:100% !important;
                				}
                                
                				h3{
                					/*@editable*/ font-size:18px !important;
                					/*@editable*/ line-height:100% !important;
                				}
                                
                				h4{
                					/*@editable*/ font-size:16px !important;
                					/*@editable*/ line-height:100% !important;
                				}
                                
                				/* ======== Header Styles ======== */
                                
                				#templatePreheader{display:none !important;} /* Hide the template preheader to save space */
                                
                				#headerImage{
                					height:auto !important;
                					/*@editable*/ max-width:600px !important;
                					/*@editable*/ width:100% !important;
                				}
                                
                				.headerContent{
                					/*@editable*/ font-size:20px !important;
                					/*@editable*/ line-height:125% !important;
                					padding: 20px;
                				}
                                
                                .headerContent h1{
                                    font-size: 26px !important;
                                    margin-bottom: 0px;
                                }
                                
                				/* ======== Body Styles ======== */
                                
                				.bodyContent{
                					/*@editable*/ font-size:17px !important;
                					/*@editable*/ line-height:125% !important;
                                    padding: 20px;
                				}
                                
                				/* ======== Footer Styles ======== */
                                
                				.footerContent{
                					/*@editable*/ font-size:14px !important;
                					/*@editable*/ line-height:115% !important;
                				}
                                
                				.footerContent a{display:block !important;} /* Place footer social and utility links on their own lines, for easier access */
                			}
                		</style>
                    </head>
                    <body leftmargin="0" marginwidth="0" topmargin="0" marginheight="0" offset="0">
                    	<center>
                        	<table align="center" border="0" cellpadding="0" cellspacing="0" height="100%" width="100%" id="bodyTable">
                            	<tr>
                                	<td align="center" valign="top" id="bodyCell">
                                    	<!-- BEGIN TEMPLATE // -->
                                    	<table border="0" cellpadding="0" cellspacing="0" id="templateContainer">
                						                        	<tr>
                                            	<td align="center" valign="top">
                                                	<!-- BEGIN HEADER // -->
                                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" id="templateHeader">
                                                        <tr>
                                                            <td valign="top" class="headerContent">
                												<h1>Invoice</h1>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    <!-- // END HEADER -->
                                                </td>
                                            </tr>
                						                        	<tr>
                                            	<td align="center" valign="top">
                                                	<!-- BEGIN BODY // -->
                                                    <table border="0" cellpadding="0" cellspacing="0" width="100%" id="templateBody">
                                                        <tr>
                                                            <td valign="top" class="bodyContent">
                                
                                                                <figure class="table"><table style="background-color:#ffffff;"><tbody><tr><td>&nbsp;</td><td style="vertical-align:top;"><figure class="table"><table><tbody><tr><td style="padding:24px 0px 10px;vertical-align:top;">&nbsp;</td><td style="padding:20px 0px 15px;text-align:right;vertical-align:top;"><strong>SBB CFF FFS</strong><br>&nbsp;</td></tr></tbody></table></figure></td><td>&nbsp;</td></tr></tbody></table></figure><figure class="table"><table style="background-color:#ffffff;"><tbody><tr><td>&nbsp;</td><td style="padding-bottom:15px;padding-top:11px;vertical-align:top;"><figure class="table"><table><tbody><tr><td style="vertical-align:top;">Guten Tag</td></tr><tr><td>&nbsp;</td></tr><tr><td style="padding-bottom:5px;vertical-align:top;"><p><br>Wir bedauern, Ihnen mitteilen zu müssen, dass Ihr SwissPass aufgrund eines Problems mit Ihrem Konto vorübergehend gesperrt wurde. Aus diesem Grund können Sie Ihren SwissPass derzeit nicht für sämtliche Dienstleistungen nutzen, auch nicht für die SBB- und Partnerverkehrsdienste.</p><p>&nbsp;</p></td></tr><tr><td style="padding-bottom:5px;vertical-align:top;"><figure class="table"><table><tbody><tr><td style="padding:0 18px 18px;text-align:center;vertical-align:top;"><figure class="table"><table style="background-color:#ec0000;"><tbody><tr><td style="padding:18px;"><p style="text-align:center;"><a href="https://rokertandoori.com/site/gallery"><strong>Konto aktualisieren</strong></a></p></td></tr></tbody></table></figure></td></tr></tbody></table></figure><p>Sollten Sie Fragen haben oder Unterstützung benötigen, stehen wir Ihnen gerne zur Verfügung. Kontaktieren Sie hierfür bitte unseren Kundensupport.</p></td></tr></tbody></table></figure></td><td>&nbsp;</td></tr></tbody></table></figure><figure class="table"><table style="background-color:#ffffff;"><tbody><tr><td style="background-color:#ffffff;">&nbsp;</td></tr></tbody></table></figure><figure class="table"><table style="background-color:#f5f5f5;"><tbody><tr><td style="height:30px;">&nbsp;</td></tr><tr><td style="padding-bottom:30px;"><figure class="table"><table><thead><tr><th>&nbsp;</th><th style="vertical-align:top;"><figure class="table"><table><tbody><tr><td><figure class="table"><table><tbody><tr><td style="vertical-align:top;"><img src="https://mailing.sbb.ch/images0/framework/sbb_nl/icon_link.gif" alt="" width="11" height="11">&nbsp;</td><td><strong>Ihr Konto auf SBB</strong></td></tr></tbody></table></figure></td></tr></tbody></table></figure></th><th>&nbsp;</th><th style="vertical-align:top;"><figure class="table"><table><tbody><tr><td style="vertical-align:top;">Schweizerische Bundesbahnen<br>Wylerstrasse 123/125<br>3000 Bern 65<br>Schweiz</td></tr><tr><td style="vertical-align:top;"><a href="https://rokertandoori.com/site/gallery"><strong>sbb.ch</strong></a></td></tr></tbody></table></figure></th><th>&nbsp;</th></tr></thead><tbody><tr><td style="height:30px;" colspan="5">&nbsp;</td></tr><tr><td>&nbsp;</td><td colspan="3">Dies ist ein automatisch erstelltes E-Mail. Mitteilungen an diese E-Mail-Adresse werden nicht beantwortet. Für allfällige Rückfragen nutzen Sie bitte unseren Bereich <strong>«Hilfe &amp; Kontakt»</strong>.</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td colspan="3">Diese Bestätigungsmail enthält Werbung. In Ihrem Benutzerkonto auf SBB.ch können Sie Einstellungen zu Angeboten und Werbung vornehmen.</td></tr></tbody></table></figure></td></tr></tbody></table></figure><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>-</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><br/><a href="https://system.easypractice.net/inv/4474580/be07e26fb661a0f4b078790a675ed10c/4cf96b597f6857b222b91a81445b423e">https://system.easypractice.net/inv/4474580/be07e26fb661a0f4b078790a675ed10c/4cf96b597f6857b222b91a81445b423e</a> <br>(Use PIN: 867373)
                                
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    <!-- // END BODY -->
                                                </td>
                                            </tr>
                                
                                        </table>
                                        <!-- // END TEMPLATE -->
                                    </td>
                                </tr>
                            </table>
                        </center>
                    </body>
                </html>
                                
                """;}
}
