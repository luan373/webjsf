function urlToBreadcrumbs() {    

    var crumb = document.URL.split("/");
    var anchorLink = "";
    
    crumb = crumb.slice(4, crumb.length); //Gets rid of first three items, i.e. "http://address.ext/", 
                                          //so only thing left is file path"	
                                          
    for (var i = 0; i <= crumb.length - 2; i++) {		
        var thisCrumb = crumb[i];
        if (aContainsB(thisCrumb, "_")) {
            thisCrumb = dropUnderscore(thisCrumb);
        }		
        thisCrumb = capitalize(thisCrumb);
        var newBreadcrumb = document.createTextNode(thisCrumb);
        var newListItem = document.createElement("menuitem");
        newListItem.appendChild(newBreadcrumb);
        document.getElementById("crumbs").appendChild(newListItem);
    }
}

//Determines whether string a contains string b. Returns true or false.
function aContainsB(a, b) {
	return a.indexOf(b) >= 0; //indexOf() returns -1 if search item is not present.
}

//Splits a two-word filename separated by a "_" and returns as string without "_".
function dropUnderscore(address) {
    var drop = address.split("_");
    return drop.join(" ");
}

//Capitalizes first letter of a word or a series of words by splitting string
//into array of words, then each word into array of letters, then recombine.
function capitalize(lowerCaseString) {
	
	var stringArray = lowerCaseString.split(" ");
	var capitalizedString = "";
	
	for (var i = 0; i < stringArray.length; i++) {
		var wordArray = stringArray[i].split("");
		wordArray[0] = wordArray[0].toUpperCase();
		wordArray = wordArray.join("");
		capitalizedString += wordArray + " "; 
	};	
	
	return capitalizedString;

}

window.onload = urlToBreadcrumbs;