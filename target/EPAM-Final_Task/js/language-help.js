// This script assigns the current servlet URL for language-related inputs, so the LanguageServlet
// will be able to redirect to the page from where the command to change the language was sent
document.getElementById("loc").setAttribute('value',window.location.href);
document.getElementById("loc2").setAttribute('value',window.location.href);