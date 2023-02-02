$(document).ready(function(){
    let signinEmp = $(".links").find("li").find("#signinEmp") ; 
    let signin = $(".links").find("li").find("#signin") ;
    let first_input = $("form").find(".first-input");
    let hidden_input = $("form").find(".input__block").find("#repeat__password");
    let signin_btn  = $("form").find(".signin__btn");
  
    //----------- signinEmp ---------------------
    signinEmp.on("click",function(e){
      e.preventDefault();
      $(this).parent().parent().siblings("h1").text("LOGIN");
      $(this).parent().css("opacity","1");
      $(this).parent().siblings().css("opacity",".6");
      first_input.removeClass("first-input__block").addClass("signinEmp-input__block");
      hidden_input.css({
        "opacity" : "1",
        "display" : "block"
      });
      signinEmp_btn.text("로그인");
    });
    
  
   //----------- sign in ---------------------
   signin.on("click",function(e){
      e.preventDefault();
      $(this).parent().parent().siblings("h1").text("LOGIN");
      $(this).parent().css("opacity","1");
      $(this).parent().siblings().css("opacity",".6");
      first_input.addClass("first-input__block")
        .removeClass("signinEmp-input__block");
      hidden_input.css({
        "opacity" : "0",
        "display" : "none"
      });
      signin_btn.text("로그인");
    });
});