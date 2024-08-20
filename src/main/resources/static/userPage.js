const userurl = 'api/user';

const authUser = fetch(userurl).then(response => response.json())
authUser.then(users => {
        let roles = '';
        users.roles.forEach(role => {
            roles += ' '
            roles += role.name
        })
        document.getElementById("navbar-username").innerHTML = users.username
        document.getElementById("navbar-roles").innerHTML = roles
    }
)

async function getUserPage() {
    let page = await fetch(userurl);

    if (page.ok) {
        let user = await page.json();
        getInformationAboutUser(user);
    } else {
        alert(`Error, ${page.status}`)
    }
}

function getInformationAboutUser(user) {
    const tableBody = document.getElementById('userOneBody');
    let dataHtml;
    let roles = [];
    console.log('userData', JSON.stringify(user))
    for (let role of user.roles) {
        roles.push(" " + role.name.toString()
            .replaceAll("ROLE_", ""))
    }

    dataHtml =
        `<tr>
    <td>${user.id}</td>
    <td>${user.username}</td>
    <td>${user.lastname}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${roles.join(',')}</td>

</tr>`;

    tableBody.innerHTML = dataHtml;
}

getUserPage();