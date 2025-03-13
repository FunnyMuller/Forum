function trip(obj, trip) {
    document.getElementById(obj).innerHTML = "<b>" + trip + "</b>";
}

function checkForm() {
    // 获取用户名输入项
    let userName = document.getElementById("userName");
    let uName = userName.value;
    if (uName.length < 1 || uName.length > 20) {
        trip("name_trip", "账号长度为1-10位!");
        return false;
    }

    // 密码长度大于6 和确认必须一致
    let password = document.getElementById("password");
    let userPass = password.value;
    if (userPass.length < 6) {
        trip("password_trip", "密码要大于6位!");
        return false;
    }

    // 确认密码
    let surePassword = document.getElementById("confirmPassword");
    let surePass = surePassword.value;
    if (userPass !== surePass) {
        trip("surePassword_trip", "密码不一致,请重新输入");
        return false;
    }

    //校验邮箱:/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
    let inputEmail = document.getElementById("email");
    let uEmail = inputEmail.value;
    if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test(uEmail)) {
        trip("email_trip", "邮箱不合法!");
        return false;
    }
    return true;
}

function submitT() {
    return checkForm();
}
