// eslint-disable-next-line no-unused-vars
import React, {useState, useEffect} from "react";
import axios from "axios";

function Course() {
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [courseList, setCourseList] = useState([]);
    const [courseIdList, setCourseIdList] = useState([]);
    const [results, setResults] = useState([]);

    useEffect(() => {
        setLoading(true);
        axios.get("http://localhost:8080/courses")
            .then((res) => {
                setCourseList(res.data);
                setLoading(false);
            }, (err) => {
                setError(err);
                setLoading(false);
            })
    }, []);

    function handleClick(id) {
        if (!courseIdList.includes(id)) {
            setCourseIdList(l => [...l, id]);
        } else {
            setCourseIdList(l => l.filter((p_id) => p_id !== id));
        }
    }

    function postSelectedList() {
        setResults(r => r.filter((r) => r != null));
        setLoading(true);
        const courseIdListToPost = {
            "courseIds": courseIdList,
        };
        axios.post("http://localhost:8080/programs/all", courseIdListToPost).then((res) => {
            setResults(res.data);
            setLoading(false);
        }, (err) => {
            setError(err);
            setLoading(false);
        })
    }

    if (error) {
        return (<div>{error}</div>);
    } else if (loading) {
        return (<div>Loading...</div>);
    } else {
        return (
            <div>
                <ul>
                    {courseList.map(course => (
                            <li onClick={() => handleClick(course.id)} key={course.id}>
                                {course.name}
                            </li>
                        )
                    )}
                </ul>
                <h1>Selected</h1>
                <ul>
                    {courseIdList.map(id => (
                            <li key={id}>
                                {id}
                            </li>
                        )
                    )}
                </ul>
                <button onClick={postSelectedList}>Create</button>
                <ul>
                    {results.map(program => (
                        <li key={program.id}>Program {program.id} :
                            <div>
                                <ul>
                                    {program["programSections"].map(section => (
                                        <li key={section.id}>Section No: {section["sectionNo"]}</li>
                                    ))}
                                </ul>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>

        );
    }
}

export default Course