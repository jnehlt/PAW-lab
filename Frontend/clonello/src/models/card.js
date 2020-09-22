export default class Card {
    constructor(listId, name, content, description, complete, deadline) {
        this.listId = listId;
        this.name = name;
        this.content = content;
        this.description = description;
        this.complete = complete;
        this.deadline = deadline;
    }
}