<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="messages">
</div>

<script type="text/javascript">

  function showReply(i){
    $("#form"+i).toggle();
  }

  function showMessages(data) {
    $("#messages").html("");
    for (var i=0;i<data.messages.length;i++){
      var message = data.messages[i];

      var messageDiv = document.createElement("div");
      messageDiv.setAttribute("class","message");

      var subjectSpan = document.createElement("span");
      subjectSpan.setAttribute("class","subject");
      subjectSpan.appendChild(document.createTextNode(message.subject));

      var contentSpan = document.createElement("span");
      contentSpan.setAttribute("class","messageBody");
      contentSpan.appendChild(document.createTextNode(message.content));

      var nameSpan = document.createElement("span");
      nameSpan.setAttribute("class","messageBody");
      nameSpan.appendChild(document.createTextNode(message.name + "("));

      var link = document.createElement("a");
      link.setAttribute("class","link");
      link.setAttribute("href","#");
      link.setAttribute("onclick","showReply("+i+")");
      link.appendChild(document.createTextNode(message.email));
      nameSpan.appendChild(link);
      nameSpan.appendChild(document.createTextNode(")"));

      var replyForm = document.createElement("form");
      replyForm.setAttribute("class","replyForm");
      replyForm.setAttribute("id","form"+i)

      var textArea = document.createElement("textarea");
      textArea.setAttribute("class","textarea");

      var replyButton = document.createElement("input");
      replyButton.setAttribute("class","replyButton");
      replyButton.setAttribute("type","button");
      replyButton.setAttribute("value","Reply");

      replyForm.appendChild(textArea);
      replyForm.appendChild(replyButton);

      messageDiv.appendChild(subjectSpan);
      messageDiv.appendChild(contentSpan);
      messageDiv.appendChild(nameSpan);
      messageDiv.appendChild(replyForm);

      $("div#messages").append(messageDiv);
    }
  }

  function updatePage(){
    $.getJSON("<c:url value="/getmessages"/>", showMessages)
  }
  function onLoad() {
    updatePage();
    window.setInterval(updatePage,10000);
  }

  $(document).ready(onLoad);
</script>
