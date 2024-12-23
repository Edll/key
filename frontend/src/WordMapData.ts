export interface WordMapData {
    [id: number]: {
        postId: number
        wordMap: {
            [word: string]: number
        }
    }
}