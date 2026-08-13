node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/lxmlport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/lxmlport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'XML and XSLT processing for Python, built on libxml2 and libxslt'),
      string(name: 'BUILD_LINE', value: 'DEV'),
      booleanParam(name: 'PUBLISH_PYTHON_WHEEL', value: true)
    ]
  }
}
