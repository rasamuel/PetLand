document.addEventListener('DOMContentLoaded', function () {
    var questions = document.querySelectorAll('.faq-question');
    questions.forEach(function (question) {
      question.addEventListener('click', function () {
        var answer = this.nextElementSibling;
        answer.style.display = answer.style.display === 'block' ? 'none' : 'block';
      });
    });
  });
  
document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Evita el envío del formulario

    // Obtiene los valores del formulario
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const message = document.getElementById('message').value;

    // Valida los campos
    if (!name || !email || !message) {
        displayResponseMessage('Por favor, complete todos los campos.');
        return;
    }

    // Muestra un mensaje de éxito
    displayResponseMessage('¡Mensaje enviado con éxito!');

    // Limpia el formulario
    document.getElementById('contactForm').reset();
});

function displayResponseMessage(message) {
    const responseMessageElement = document.getElementById('responseMessage');
    responseMessageElement.textContent = message;
}
