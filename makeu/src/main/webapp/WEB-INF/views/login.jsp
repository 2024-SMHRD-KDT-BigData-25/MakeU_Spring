<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/eystyle.css">
        <title>login</title>
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <div class="main_content">
            <div class="login_form">
                <form action="">
                    <table class="login_form_table">
                        <tr>
                            <td class="login_title nanum-gothic-regular" colspan="2">Login
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="text" placeholder="아이디를 입력해주세요"><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="password" placeholder="비밀번호를 입력해주세요"><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button id="login_btn">Login</button>
                            </td>
                        </tr>
                        <tr>
                            <td class="login_more">비밀번호 찾기</td>
                            <td class="login_more" onclick="location.href='join'">회원이 아니신가요?</td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>

    </html>