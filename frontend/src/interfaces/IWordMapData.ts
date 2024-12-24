export interface IWordMapData {
    [id: number]: IWordMapEntry
}

export interface IWordMapEntry {
        postId: number
        postTitle: string
        postLink: string
        postAuthor: string
        postDate: number
        wordMap: {
            [word: string]: number
        }
}