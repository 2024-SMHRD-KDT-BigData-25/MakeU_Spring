<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/eystyle.css">
        <title>join</title>
    </head>

    <body>
        <jsp:include page="header.jsp" />
        <div class="main_content">
            <div class="login_form">
                <form action="/makeu/members" method="post">
                    <table class="login_form_table">
                        <tr>
                            <td class="login_title nanum-gothic-regular" colspan="2">Join
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="text" placeholder="아이디를 입력해주세요" name="mem_id" id="mem_id"><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="password" placeholder="비밀번호를 입력해주세요" name="mem_pw" id="mem_pw"><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="password" placeholder="비밀번호 확인" name="pw_confirm" id="pw_confirm"><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="text" placeholder="이름을 입력해주세요" name="mem_name" id="mem_name"><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="text" placeholder="닉네임을 입력해주세요" name="mem_nickname" id="mem_nickname"><br>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button id="login_btn">Submit</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>

    </html>