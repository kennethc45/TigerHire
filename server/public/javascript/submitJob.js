const jobPostingData = {
    jobTitle: '',
    companyName: '',
    location: '',
    remoteType: '',
    salary: '',
    questions: []
}

function addQuestion() {
    const question = prompt('Type question here')
    if (question) {
        jobPostingData.questions.push(question);
        renderQuestions();
    }
}

function renderQuestions() {
    const questionsContainer = document.getElementById('questionsContainer')
    questionsContainer.innerHTML = ''

    jobPostingData.questions.forEach((question, i) => {
        const questionDiv = document.createElement('div')
        questionDiv.textContent = `Question ${i + 1}: ${question}`;
        questionsContainer.appendChild(questionDiv)
    });
}

function submitJobPosting() {
    jobPostingData.jobTitle = document.getElementById('jobTitle').value
    jobPostingData.companyName = document.getElementById('companyName').value
    jobPostingData.location = document.getElementById('location').value
    jobPostingData.remoteType = document.getElementById('remoteType').value
    jobPostingData.salary = document.getElementById('salary').value

    console.log(jobPostingData);
}